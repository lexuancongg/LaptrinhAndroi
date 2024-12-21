package com.example.bai1.Fragment1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bai1.R;
import com.example.bai1.adapter.GridDanhMucAdapter;
import com.example.bai1.api.ApiService;
import com.example.bai1.api.RetrofitClient;
import com.example.bai1.utils.Final;
import com.example.bai1.utils.GetTokenId;
import com.example.bai1.viewmodel.CategoryVm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyDanhMuc extends AppCompatActivity {
    private ArrayList<CategoryVm> categories = new ArrayList<>();
    private GridView gridView;
    private RelativeLayout blockGridViewQuanLyDanhMuc;
    private GridDanhMucAdapter adapter;
    private Long selectedCategoryId;
    private String nameCategorySelected;
    private Button btnEdit, btnDelete;
    private EditText et_edit_category;
    private String nameUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydanhmuc); // Set layout của Activity

        // Initialize views
        gridView = findViewById(R.id.gridViewQldm);
        blockGridViewQuanLyDanhMuc = findViewById(R.id.blockGirlViewQuanlydanhmuc);
        et_edit_category = findViewById(R.id.et_edit_category);
        btnEdit = findViewById(R.id.edit);
        btnDelete = findViewById(R.id.delete);
        // Initialize adapter
        adapter = new GridDanhMucAdapter(this, categories);
        gridView.setAdapter(adapter);

        // Fetch categories
        fetchCategories();

        // Handle item click
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            adapter.setSelectedPosition(position);
            CategoryVm selectedCategory = categories.get(position);
            selectedCategoryId = selectedCategory.getId(); // Lưu I
            this.nameCategorySelected = selectedCategory.getName();
            this.et_edit_category.setText(this.nameCategorySelected);

        });
        btnEdit.setOnClickListener(v -> {
            if(this.selectedCategoryId==null ){
                Toast.makeText(QuanLyDanhMuc.this, "Vui lòng chọn danh mục cần sửa", Toast.LENGTH_SHORT).show();
                return;
            }else {
                if (et_edit_category.getText().toString().trim().isEmpty()){
                    Toast.makeText(QuanLyDanhMuc.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            this.nameUpdate = et_edit_category.getText().toString().trim();
            GetTokenId.get(new GetTokenId.TokenCallback() {
                @Override
                public void onTokenReceived(String token) {
                    handleEditCategory(token,selectedCategoryId,nameUpdate);
                }

                @Override
                public void onFailure(Exception e) {

                }
            });


        });
        btnDelete.setOnClickListener(v -> {
            if(this.selectedCategoryId==null){
                Toast.makeText(QuanLyDanhMuc.this, "Vui lòng chọn danh mục cần sửa", Toast.LENGTH_SHORT).show();
                return;
            }
            GetTokenId.get(new GetTokenId.TokenCallback() {
                @Override
                public void onTokenReceived(String token) {
                    handleDeleteCategory(token,selectedCategoryId);
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        });
    }
    private void handleEditCategory(String token,Long selectedCategoryId,String nameUpdate){
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.editCategory("Bearer "+ token ,selectedCategoryId,nameUpdate).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    fetchCategories();
                }else{
                    Toast.makeText(QuanLyDanhMuc.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(QuanLyDanhMuc.this, "Xóa thất bại vì tồn tại giao dịch", Toast.LENGTH_SHORT).show();


            }
        });


    }
    private void handleDeleteCategory(String token,Long selectedCategoryId){
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.deleteCategory("Bearer "+ token ,selectedCategoryId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    fetchCategories();
                }else{
                    Toast.makeText(QuanLyDanhMuc.this, "Xóa thất bại vì tồn tại giao dịch", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(QuanLyDanhMuc.this, "Xóa thất bại vì tồn tại giao dịch", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void fetchCategories() {
        GetTokenId.get(new GetTokenId.TokenCallback() {
            @Override
            public void onTokenReceived(String token) {
                ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
                apiService.getAllCAtegory("Bearer " + token).enqueue(new Callback<List<CategoryVm>>() {
                    @Override
                    public void onResponse(Call<List<CategoryVm>> call, Response<List<CategoryVm>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            categories.clear();
                            categories.addAll(response.body());
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.e("API Error", "Failed to fetch categories: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CategoryVm>> call, Throwable t) {
                        Log.e("API Error", "Error fetching categories", t);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("Token Error", "Error fetching token", e);
            }
        });
    }
}
