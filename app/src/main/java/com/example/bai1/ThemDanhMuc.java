package com.example.bai1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bai1.input_fragment.TienChiFragment;
import com.example.bai1.input_fragment.TienThuFragment;
import com.example.bai1.ThemDanhMuc_Tab_Fragment.ThemDanhMuc_Adapter; // Đừng quên import adapter của bạn
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThemDanhMuc extends AppCompatActivity {
    TabLayout tabLayout;
    ImageButton imageButton;
    private ViewPager2 viewPager2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_danh_muc);

        // Khởi tạo TabLayout và ViewPager2
        tabLayout = findViewById(R.id.tab_layout_themDanhMuc); // Khởi tạo TabLayout
        viewPager2 = findViewById(R.id.themDanhMuc_viewpager); // Khởi tạo ViewPager2
        ThemDanhMuc_Adapter adapter = new ThemDanhMuc_Adapter(this); // Tạo adapter

        viewPager2.setAdapter(adapter); // Gán adapter cho ViewPager2

        // Xử lý sự kiện cho nút quay lại
        imageButton = findViewById(R.id.id_previous);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemDanhMuc.this, TienChiFragment.class);
                startActivity(intent);
            }
        });

        // Gán TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tiền Chi");
                    break;
                case 1:
                    tab.setText("Tiền Thu");
                    break;
            }
        }).attach();
    }
}
