package com.example.bai1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityReportList extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_report_list);
        viewPager = findViewById(R.id.viewPager1);
        tabLayout = findViewById(R.id.tabLayout1);
        viewPagerAdapter = new ViewPagerAdapter(this);
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
        ImageView trove3 = findViewById(R.id.trove3);
        trove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
