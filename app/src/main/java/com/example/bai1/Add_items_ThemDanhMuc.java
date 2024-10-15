package com.example.bai1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bai1.Fragment1.GridViewAdapter;
import com.example.bai1.ThemDanhMuc_Tab_Fragment.TienChiDanhMucFragment;

public class Add_items_ThemDanhMuc extends AppCompatActivity {
    String[] ten = {
            "Thêm", "Tiền nhà", "Dự phòng", "Tiền ăn",
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn","Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",

    };
    GridView gridView;
    int[] hinh = {
            R.drawable.baseline_add_circle_outline_24, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,

    };
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_items_them_danh_muc);

        // Khởi tạo GridView và adapter ở đây
        gridView = findViewById(R.id.gridView_Taomoi); // Sử dụng view đã inflate
        GridViewAdapter adapter = new GridViewAdapter(Add_items_ThemDanhMuc.this, ten, hinh);
        gridView.setAdapter(adapter);

        //imgbutton
        imageButton = findViewById(R.id.id_previous2);
        //sk imgBtn
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });
    }
}