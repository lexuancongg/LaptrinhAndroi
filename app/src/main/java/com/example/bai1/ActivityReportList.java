package com.example.bai1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bai1.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityReportList extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private EditText etYear;
    private Button btnView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        // Khởi tạo các thành phần giao diện
        etYear = findViewById(R.id.et_year);
        btnView = findViewById(R.id.btn_view);
        viewPager = findViewById(R.id.viewPager1);
        tabLayout = findViewById(R.id.tabLayout1);

        // Nút "Xem" xử lý sự kiện
        btnView.setOnClickListener(v -> {
            String year = etYear.getText().toString().trim();

            if (TextUtils.isEmpty(year)) {
                Toast.makeText(this, "Vui lòng nhập năm", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hiển thị TabLayout và ViewPager2
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            // Thiết lập ViewPager và TabLayout
            setupViewPagerAndTabs();
        });

        // Nút "Trở về"
        ImageView trove3 = findViewById(R.id.trove3);
        trove3.setOnClickListener(v -> finish());


        TabLayout tabLayout = findViewById(R.id.tabLayout1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Khi tab được chọn
                tab.view.setBackgroundColor(getResources().getColor(R.color.tabBackgroundSelected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Khi tab bị bỏ chọn
                tab.view.setBackgroundColor(getResources().getColor(R.color.tabBackgroundUnselected));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Khi tab đã được chọn lại (không làm gì trong trường hợp này)
            }
        });

    }

    private void setupViewPagerAndTabs() {
        String year = etYear.getText().toString().trim();

        // Cấu hình ViewPager2 và TabLayout
        viewPagerAdapter = new ViewPagerAdapter(this, year); // Truyền year vào Adapter
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Chi tiêu");
                    break;
                case 1:
                    tab.setText("Thu nhập");
                    break;
            }
        }).attach();
    }

}
