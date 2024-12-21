
package com.example.bai1.input_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.bai1.Add_items_ThemDanhMuc;
import com.example.bai1.adapter.GridDanhMucAdapter;
import com.example.bai1.R;
import com.example.bai1.api.ApiService;
import com.example.bai1.api.RetrofitClient;
import com.example.bai1.utils.Final;
import com.example.bai1.utils.GetTokenId;
import com.example.bai1.viewmodel.CategoryVm;
import com.example.bai1.viewmodel.TransactionPostVm;
import com.example.bai1.viewmodel.TransactionVm;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TienThuFragment extends Fragment {
    ArrayList<CategoryVm> categories = new ArrayList<>();
    GridView gridView;
    private Button btn_nhapthu;
    private EditText et_ngay,et_note,et_amount;
    private Long selectedCategoryId;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tien_thu, container, false);


        gridView = view.findViewById(R.id.gridView_tienthu); // Sử dụng view đã inflate
//        GridViewAdapter adapter = new GridViewAdapter(requireContext(), ten, hinh_thu);
        GridDanhMucAdapter adapter = new GridDanhMucAdapter(requireContext(), categories);

        gridView.setAdapter(adapter);



        et_ngay = view.findViewById(R.id.ngay_tienthu);
        et_note = view.findViewById(R.id.note_tienthu);
        et_amount = view.findViewById(R.id.tien_tienthu);



        GetTokenId.get(new GetTokenId.TokenCallback() {
            @Override
            public void onTokenReceived(String token) {
                fetchApiGetCategory(token);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Cập nhật vị trí đã chọn trong Adapter
                adapter.setSelectedPosition(position);
                CategoryVm selectedCategory = categories.get(position);
                selectedCategoryId = selectedCategory.getId(); // Lưu ID vào biến toàn cục
            }
        });

        ImageView addCategory = view.findViewById(R.id.iv_add_danhmuc);
        addCategory.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Add_items_ThemDanhMuc.class); // Sử dụng requireContext()
            startActivity(intent);
        });
        this.btn_nhapthu = view.findViewById(R.id.btn_nhapthu);
        btn_nhapthu.setOnClickListener(v -> {
            GetTokenId.get(new GetTokenId.TokenCallback() {
                @Override
                public void onTokenReceived(String token) {
                    sendApiRequestCreateTransaction(token);

                }

                @Override
                public void onFailure(Exception e) {
                    // Xử lý lỗi
                    Log.e("Token", "Error getting token", e);
                }
            });
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        GetTokenId.get(new GetTokenId.TokenCallback() {
            @Override
            public void onTokenReceived(String token) {
                fetchApiGetCategory(token); // Gọi API để cập nhật lại Adapter
            }

            @Override
            public void onFailure(Exception e) {
                // Xử lý lỗi nếu cần
            }
        });
    }


    private void fetchApiGetCategory(String token) {
        // Khởi tạo Retrofit và API Service
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        // Gọi API để lấy danh mục
        apiService.getCategoryVmByTab("Bearer " + token, Final.income).enqueue(new retrofit2.Callback<List<CategoryVm>>() {
            @Override
            public void onResponse(Call<List<CategoryVm>> call, Response<List<CategoryVm>> response) {
                if (response.isSuccessful()) {
                    List<CategoryVm> categoryVmList = response.body();
                    if (categoryVmList != null) {
                        // Xử lý dữ liệu và cập nhật categories
                        categories.clear();
                        for (CategoryVm categoryVm : categoryVmList) {
                            categories.add(categoryVm); // Thêm tên danh mục vào danh sách
                        }

                        // Cập nhật Adapter
                        GridDanhMucAdapter adapter = (GridDanhMucAdapter) gridView.getAdapter();
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }

                    }
                } else {
                    Log.e("API Response", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CategoryVm>> call, Throwable t) {
                // Xử lý lỗi
                Log.e("API Request Error", "Error during API request", t);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendApiRequestCreateTransaction(String token) {

        // Lấy dữ liệu từ các trường nhập liệu
        String dateStr = et_ngay.getText().toString().trim();
        String note = et_note.getText().toString().trim();
        String amountStr = et_amount.getText().toString().trim();


        // Kiểm tra dữ liệu đầu vào
        if (dateStr.isEmpty() || note.isEmpty() || amountStr.isEmpty()) {
            Log.e("Validation Error", "Date, Note, or Amount field is empty.");
            return; // Ngừng thực hiện nếu dữ liệu không hợp lệ
        }

        try {
            // Định dạng ngày nhập từ EditText (d/M/yyyy)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

            // Chuyển đổi chuỗi ngày thành LocalDate
            LocalDate localDate = LocalDate.parse(dateStr, formatter);

            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh"));

// Định dạng lại trước khi gửi lên server
            String formattedDateTime = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);

            System.out.println(formattedDateTime);

            // Chuyển đổi amount từ chuỗi thành BigDecimal
            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountStr));

            // Tạo đối tượng TransactionPostVm
            TransactionPostVm transactionPostVm = new TransactionPostVm();
            transactionPostVm.setAmount(amount);
            transactionPostVm.setSelectedCategoryId(selectedCategoryId); // Đảm bảo selectedCategoryId đã được gán giá trị
            transactionPostVm.setDateTime(formattedDateTime);
            transactionPostVm.setNote(note);
            transactionPostVm.setType(Final.income);


            // Tạo Retrofit client và gọi API
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            apiService.createTransaction("Bearer " + token, transactionPostVm).enqueue(new retrofit2.Callback<TransactionVm>() {
                @Override
                public void onResponse(Call<TransactionVm> call, Response<TransactionVm> response) {
                    if (response.isSuccessful()) {
                        // Xử lý thành công
                        TransactionVm transactionVm = response.body();
                        if (transactionVm != null) {
                            Log.d("API Response", "Transaction created successfully: " + transactionVm.toString());

                            // Reset các trường nhập liệu
                            et_amount.setText("");
                            et_ngay.setText("");
                            et_note.setText("");
                        }
                    } else {
                        // Xử lý lỗi phản hồi không thành công
                        Log.e("API Response Error", "Response code: " + response.code());
                        try {
                            if (response.errorBody() != null) {
                                Log.e("API Response Error Body", response.errorBody().string());
                            }
                        } catch (IOException e) {
                            Log.e("Error Parsing", "Error reading error body", e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<TransactionVm> call, Throwable t) {
                    // Xử lý lỗi khi không kết nối được server
                    Log.e("API Request Error", "Failed to connect to server", t);
                }
            });
        } catch (Exception e) {
            // Xử lý lỗi khi định dạng hoặc parse dữ liệu không thành công
            Log.e("Data Conversion Error", "Error parsing input data", e);
        }
    }
}