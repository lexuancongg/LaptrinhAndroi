package com.example.bai1.input_fragment;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bai1.R;
import com.example.bai1.adapter.GridDanhMucAdapter;
import com.example.bai1.api.ApiService;
import com.example.bai1.api.RetrofitClient;
import com.example.bai1.utils.Final;
import com.example.bai1.utils.GetTokenId;
import com.example.bai1.viewmodel.CategoryPostVm;
import com.example.bai1.viewmodel.CategoryVm;
import com.example.bai1.viewmodel.TransactionPostVm;
import com.example.bai1.viewmodel.TransactionVm;
import com.google.firebase.auth.FirebaseAuth;


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

public class TienChiFragment extends Fragment {
    ArrayList<CategoryVm> categories = new ArrayList<>();
    GridView gridView;
    int initialHeight;
    private FirebaseAuth mAuth;
    private RelativeLayout blockGirl;
    private  LinearLayout layoutAddCategory;
    private Button btn_nhapChi;
    private EditText et_ngay,et_note,et_amount,etNewCategory;
    private Long selectedCategoryId;
    private  GridDanhMucAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tien_chi, container, false);


        // Khởi tạo GridView và adapter ở đây
        gridView = view.findViewById(R.id.gridView); // Sử dụng view đã inflate
         adapter = new GridDanhMucAdapter(requireContext(), categories);
        gridView.setAdapter(adapter);


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
        btn_nhapChi = view.findViewById(R.id.btn_nhapChi);
        et_ngay = view.findViewById(R.id.et_ngay);
        et_note = view.findViewById(R.id.et_note);
        et_amount = view.findViewById(R.id.et_amount);


        mAuth = FirebaseAuth.getInstance();




        ImageView addCategory = view.findViewById(R.id.iv_add_danhmuc_chi);
         layoutAddCategory = view.findViewById(R.id.layout_add_category);
        etNewCategory = view.findViewById(R.id.et_new_category);
        Button btnAddCategory = view.findViewById(R.id.btn_add_category);
         blockGirl = view.findViewById(R.id.blockGirl);
        blockGirl.post(() -> initialHeight = blockGirl.getHeight());


        btnAddCategory.setOnClickListener(v -> {
            GetTokenId.get(new GetTokenId.TokenCallback() {
                @Override
                public void onTokenReceived(String token) {
                    sendApiRequest(token);
                }

                @Override
                public void onFailure(Exception e) {
                    // Xử lý lỗi
                    Log.e("Token", "Error getting token", e);
                }
            });


        });
        btn_nhapChi.setOnClickListener(v -> {
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


        addCategory.setOnClickListener(v -> {

             if (layoutAddCategory.getVisibility() == View.GONE) {
                 layoutAddCategory.setVisibility(View.VISIBLE);
                 ViewGroup.LayoutParams params = blockGirl.getLayoutParams();
                 params.height = 500;
                 blockGirl.setLayoutParams(params);
             } else {
                 layoutAddCategory.setVisibility(View.GONE);
                 ViewGroup.LayoutParams params = blockGirl.getLayoutParams();
                 params.height = initialHeight;  // Đặt chiều cao lại như lúc ban đầu
                 blockGirl.setLayoutParams(params);
             }


//            Intent intent = new Intent(requireContext(), Add_items_ThemDanhMuc.class); // Sử dụng requireContext()
//            startActivity(intent);
        });
        return view; // Đảm bảo return view sau cùng

    }
    private void sendApiRequest(String token) {
        // Khởi tạo Retrofit và API Service
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        CategoryPostVm categoryPostVm = new CategoryPostVm();
        categoryPostVm.setDescription("");
        categoryPostVm.setName(etNewCategory.getText().toString().trim());
        categoryPostVm.setType(Final.expense);
        if(etNewCategory.getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "Vui lòng nhập tên danh mục", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.createCategory("Bearer " + token,categoryPostVm).enqueue(new retrofit2.Callback<CategoryVm>() {
            @Override
            public void onResponse(Call<CategoryVm> call, Response<CategoryVm> response) {
                if (response.isSuccessful()) {
                  CategoryVm newCategory = response.body();
                    if (newCategory != null) {
                        // Thêm danh mục mới vào danh sách
                        categories.add(newCategory);

                        // Cập nhật Adapter
                        GridDanhMucAdapter adapter = (GridDanhMucAdapter) gridView.getAdapter();
                        adapter.notifyDataSetChanged();
                        layoutAddCategory.setVisibility(View.GONE);
                        ViewGroup.LayoutParams params = blockGirl.getLayoutParams();
                        params.height = initialHeight;  // Đặt chiều cao lại như lúc ban đầu
                        blockGirl.setLayoutParams(params);

                    }

                } else {
                    Log.e("API Response", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CategoryVm> call, Throwable t) {
                Log.e("API Request Error", "Error during API request", t);
            }
        });
    }
    private void fetchApiGetCategory(String token) {
        // Khởi tạo Retrofit và API Service
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        apiService.getCategoryVmByTab("Bearer " + token,Final.expense).enqueue(new retrofit2.Callback<List<CategoryVm>>() {
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
            transactionPostVm.setType(Final.expense);


            // Tạo Retrofit client và gọi API
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            apiService.createTransaction("Bearer " + token, transactionPostVm).enqueue(new retrofit2.Callback<TransactionVm>() {
                @Override
                public void onResponse(Call<TransactionVm> call, Response<TransactionVm> response) {
                    if (response.isSuccessful()) {
                        adapter.setSelectedPosition(0);
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



