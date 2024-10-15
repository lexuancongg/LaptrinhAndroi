package com.example.bai1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bai1.ThemDanhMuc_Tab_Fragment.ThemDanhMuc_Adapter;
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
        tabLayout = findViewById(R.id.tab_layout_themDanhMuc);
        viewPager2 = findViewById(R.id.themDanhMuc_viewpager);
        ThemDanhMuc_Adapter adapter = new ThemDanhMuc_Adapter(this);
        viewPager2.setAdapter(adapter);

        // Xử lý sự kiện cho nút quay lại
        imageButton = findViewById(R.id.id_previous);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemDanhMuc.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Nhận giá trị từ Intent và chuyển đến tab được chỉ định
        Intent intent = getIntent();
        int tabToOpen = intent.getIntExtra("tabToOpen", 0); // Giá trị mặc định là 0 (tab đầu tiên)
        viewPager2.setCurrentItem(tabToOpen);

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
