package com.example.bai1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.example.bai1.Fragment1.ViewPagerAdapter;
import com.example.bai1.activity.MonthlyFluctuationsActivity;
import com.example.bai1.input_fragment.TienChiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private boolean isManualPageChange = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Kiểm tra Intent để xác định fragment nào cần hiển thị
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("fragmentToShow")) {
            int fragmentToShow = intent.getIntExtra("fragmentToShow", 0);
            isManualPageChange = true;
            viewPager.setCurrentItem(fragmentToShow, false);  // Thay đổi fragment ban đầu
            bottomNavigationView.getMenu().getItem(fragmentToShow).setChecked(true); // Đảm bảo item được chọn
        }

        // Đồng bộ ViewPager với BottomNavigationView
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (!isManualPageChange) {
                    bottomNavigationView.getMenu().getItem(position).setChecked(true);
                }
                isManualPageChange = false;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                isManualPageChange = true;
                if (itemId == R.id.menu_input) {
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.menu_calendar) {
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.menu_add) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }

}