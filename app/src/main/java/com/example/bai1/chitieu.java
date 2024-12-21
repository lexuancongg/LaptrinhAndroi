package com.example.bai1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bai1.adapter.CategoryAdapterAtYear;
import com.example.bai1.api.ApiService;
import com.example.bai1.api.RetrofitClient;
import com.example.bai1.utils.Final;
import com.example.bai1.utils.GetTokenId;
import com.example.bai1.viewmodel.CategoryAtYearAmountVm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chitieu extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapterAtYear adapter;
    private List<CategoryAtYearAmountVm> categoryList = new ArrayList<>();
    private String year; // Lưu năm được truyền vào

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitieu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Nhận năm từ arguments
        if (getArguments() != null) {
            year = getArguments().getString("year");
            GetTokenId.get(new GetTokenId.TokenCallback() {
                @Override
                public void onTokenReceived(String token) {
                    sendApiBaoCaoDanhMucTheoNam(token,Integer.parseInt(year));
                }

                @Override
                public void onFailure(Exception e) {

                }
            });

        }



        adapter = new CategoryAdapterAtYear(categoryList);
        recyclerView.setAdapter(adapter);

        return view;
    }
    public void sendApiBaoCaoDanhMucTheoNam(String token,int year){
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.baocaodanhmuctheonam("Bearer "+ token,year, Final.expense).enqueue(new Callback<List<CategoryAtYearAmountVm>>() {
            @Override
            public void onResponse(Call<List<CategoryAtYearAmountVm>> call, Response<List<CategoryAtYearAmountVm>> response) {
                if(response.isSuccessful()){
                    List<CategoryAtYearAmountVm> categoryAtYearAmountVms =  response.body();
                    if (categoryAtYearAmountVms != null) {
                        // Cập nhật danh sách categoryList với dữ liệu mới
                        categoryList.clear();
                        categoryList.addAll(categoryAtYearAmountVms);

                        // Thông báo cho adapter rằng dữ liệu đã thay đổi
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CategoryAtYearAmountVm>> call, Throwable t) {

            }
        });

    }


}
