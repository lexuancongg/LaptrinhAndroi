package com.example.bai1.Fragment1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bai1.R;
import com.example.bai1.adapter.TransactionAdapter;
import com.example.bai1.api.ApiService;
import com.example.bai1.api.RetrofitClient;
import com.example.bai1.utils.Final;
import com.example.bai1.utils.GetTokenId;
import com.example.bai1.viewmodel.TransactionGetVm;
import com.example.bai1.viewmodel.TransactionVm;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExpenseFragment extends Fragment {

    private PieChart pieChart;
    private String currentDate;
    private TransactionAdapter transactionAdapter;
    private RecyclerView transactionRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        pieChart = view.findViewById(R.id.pieChart);
        transactionRecyclerView = view.findViewById(R.id.transactionRecyclerView);
        transactionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionAdapter = new TransactionAdapter(new ArrayList<>());
        transactionRecyclerView.setAdapter(transactionAdapter);

        Bundle bundle = getArguments();
        currentDate = bundle != null ? bundle.getString("selectedDate") : null;

        if (currentDate != null) {
            fetchAndUpdateChart(currentDate);
        }

        return view;
    }

    public void updateDate(String newDate) {
        if (newDate != null && !newDate.equals(currentDate)) {
            currentDate = newDate;
            fetchAndUpdateChart(newDate);
        }
    }

    private void fetchAndUpdateChart(String date) {
        // Gọi API và cập nhật biểu đồ
        GetTokenId.get(new GetTokenId.TokenCallback() {
            @Override
            public void onTokenReceived(String token) {
                loadExpenseData(token, date);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("Token", "Error getting token", e);
            }
        });
    }

    private void loadExpenseData(String token, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        String formattedDateTime = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getExpensesByDate("Bearer " + token, date, Final.expense)
                .enqueue(new Callback<List<TransactionGetVm>>() {
                    @Override
                    public void onResponse(Call<List<TransactionGetVm>> call, Response<List<TransactionGetVm>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Map<String, Float> mapTransaction = new HashMap<>();
                            response.body().forEach(transaction -> {
                                mapTransaction.merge(transaction.getCategoryName(), transaction.getAmount(), Float::sum);
                            });
                            updatePieChart(mapTransaction);
                            List<TransactionGetVm> data = response.body();
                            List<TransactionVm> transactionVmList = new ArrayList<>();
                            for (TransactionGetVm transactionGetVm : data) {
                                transactionVmList.add(TransactionVm.fromTransactionGetVm(transactionGetVm));
                            }


                            // Cập nhật danh sách giao dịch vào RecyclerView
                            transactionAdapter.updateData(transactionVmList);
                        } else {
                            Toast.makeText(requireContext(), "Không tải được dữ liệu!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TransactionGetVm>> call, Throwable t) {
                        Toast.makeText(requireContext(), "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updatePieChart(Map<String, Float> expenses) {

        ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        float totalAmount = 0f;

        // Dùng vòng lặp for thay vì forEach
        for (Map.Entry<String, Float> entry : expenses.entrySet()) {
            totalAmount += entry.getValue();
        }

        expenses.forEach((name, amount) -> {
            entries.add(new PieEntry(amount, name));

            colors.add(ColorTemplate.COLORFUL_COLORS[entries.size() % ColorTemplate.COLORFUL_COLORS.length]);
        });

        PieDataSet dataSet = new PieDataSet(entries, "Expenses");
        dataSet.setValueTextSize(18f);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        // Định dạng số tiền dưới dạng tiền tệ (VND)
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String formattedAmount = formatter.format(totalAmount) + " VND";

        pieChart.setCenterText("Expenses: " + formattedAmount);
    }
}

//
//public class ExpenseFragment extends Fragment {
//
//    private PieChart pieChart;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
//
//        // Initialize PieChart
//        pieChart = view.findViewById(R.id.pieChart);
//
//        // Nhận ngày từ Bundle
//        Bundle bundle = getArguments();
//        String selectedDate = bundle != null ? bundle.getString("selectedDate") : null;
//
//        // Gọi API và tải dữ liệu
//        if (selectedDate != null) {
//            GetTokenId.get(new GetTokenId.TokenCallback() {
//                @Override
//                public void onTokenReceived(String token) {
//                    loadExpenseData(token,selectedDate);
//
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    // Xử lý lỗi
//                    Log.e("Token", "Error getting token", e);
//                }
//            });
//        } else {
//            Toast.makeText(requireContext(), "Ngày không hợp lệ!", Toast.LENGTH_SHORT).show();
//        }
//
//        return view;
//    }
//
//    private void loadExpenseData(String token,String date) {
//
//
//        // Định dạng ngày nhập từ EditText (d/M/yyyy)
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
//
//        // Chuyển đổi chuỗi ngày thành LocalDate
//        LocalDate localDate = LocalDate.parse(date, formatter);
//
//        // Chuyển LocalDate thành ZonedDateTime với múi giờ hệ thống
//        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
//        // Định dạng ZonedDateTime theo chuẩn ISO-8601 trước khi gán
//        String formattedDateTime = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
//        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
//
//
//
//
//        // Gọi API
//        apiService.getExpensesByDate("Bearer "+ token,date, Final.expense).enqueue(new Callback<List<TransactionGetVm>>() {
//            @Override
//            public void onResponse(Call<List<TransactionGetVm>> call, Response<List<TransactionGetVm>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<TransactionGetVm> expenses = response.body();
//
//                    Map<String ,Float> mapTransaction = new HashMap<>();
//                    expenses.forEach(transactionGetVm -> {
//                        mapTransaction.merge(transactionGetVm.getCategoryName(),transactionGetVm.getAmount(),Float::sum);
//                    });
//
////                    List<TransactionGetVm> data = mapTransaction.entrySet().stream()
////                                    .map(item-> {
////                                        TransactionGetVm transactionGetVm = new TransactionGetVm();
////                                        transactionGetVm.setId(item.ge);
////                                        return transactionGetVm;
////                                    }).collect(Collectors.toList());
////
//
//                    updatePieChart(mapTransaction); // Cập nhật PieChart với dữ liệu từ API
//                } else {
//                    Toast.makeText(requireContext(), "Không tải được dữ liệu!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<TransactionGetVm>> call, Throwable t) {
//                Toast.makeText(requireContext(), "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void updatePieChart(Map<String,Float> expenses) {
//        ArrayList<PieEntry> entries = new ArrayList<>();
//        ArrayList<Integer> colors = new ArrayList<>();
//
//        expenses.forEach((name, amount) -> {
//            entries.add(new PieEntry(amount,name));
//            colors.add(ColorTemplate.COLORFUL_COLORS[entries.size() % ColorTemplate.COLORFUL_COLORS.length]);
//
//        });
//
////        // Chuẩn bị dữ liệu từ API
////        for (TransactionGetVm expense : expenses) {
////            entries.add(new PieEntry(expense.getAmount(), expense.getCategoryName()));
////            colors.add(ColorTemplate.COLORFUL_COLORS[entries.size() % ColorTemplate.COLORFUL_COLORS.length]);
////        }
//
//        // Tạo PieDataSet
//        PieDataSet dataSet = new PieDataSet(entries, "Expenses");
//        dataSet.setValueTextSize(18f);
//        dataSet.setColors(colors);
//
//        // Set PieData
//        PieData data = new PieData(dataSet);
//        pieChart.setData(data);
//        pieChart.invalidate(); // Làm mới biểu đồ
//
//        // Tùy chỉnh biểu đồ
//        pieChart.setUsePercentValues(true);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setCenterText("Expenses");
//    }
//}
