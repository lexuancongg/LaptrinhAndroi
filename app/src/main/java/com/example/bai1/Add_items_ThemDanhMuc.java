package com.example.bai1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bai1.api.ApiService;
import com.example.bai1.api.RetrofitClient;
import com.example.bai1.utils.Final;
import com.example.bai1.utils.GetTokenId;
import com.example.bai1.viewmodel.CategoryPostVm;
import com.example.bai1.viewmodel.CategoryVm;

import retrofit2.Call;
import retrofit2.Response;

public class Add_items_ThemDanhMuc extends AppCompatActivity {
    private EditText etNewCategory ;
    private Button addCategory;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_items_them_danh_muc);

        etNewCategory = findViewById(R.id.et_name);


        // Khởi tạo GridView và adapter ở đây
        addCategory = findViewById(R.id.btn_save);
        addCategory.setOnClickListener(v -> {
            GetTokenId.get(new GetTokenId.TokenCallback() {
                @Override
                public void onTokenReceived(String token) {
                    sendApiRequest(token);
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        });

        //imgbutton
        imageButton = findViewById(R.id.id_previous2);
        //sk imgBtn
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    private void sendApiRequest(String token) {
        // Khởi tạo Retrofit và API Service
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        System.out.println("token " + token);
        System.out.println("bu "+etNewCategory);
        System.out.println("giá tị la "+ etNewCategory.getText().toString().trim());
        CategoryPostVm categoryPostVm = new CategoryPostVm();
        categoryPostVm.setDescription("");
        categoryPostVm.setName(etNewCategory.getText().toString().trim());
        categoryPostVm.setType(Final.income);

        // Gửi yêu cầu GET với token trong header
        apiService.createCategory("Bearer " + token,categoryPostVm).enqueue(new retrofit2.Callback<CategoryVm>() {
            @Override
            public void onResponse(Call<CategoryVm> call, Response<CategoryVm> response) {
                if (response.isSuccessful()) {
                    CategoryVm newCategory = response.body();
                    if (newCategory != null) {
                        System.out.println("le xuan cong");
                        finish();
                    }

                } else {
                    Log.e("API Response", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CategoryVm> call, Throwable t) {
                // Xử lý khi có lỗi trong quá trình gửi yêu cầu
                Log.e("API Request Error", "Error during API request", t);
            }
        });
    }
}