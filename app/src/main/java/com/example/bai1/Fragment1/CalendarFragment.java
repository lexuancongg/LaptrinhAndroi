package com.example.bai1.Fragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bai1.R;
import com.example.bai1.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CalendarFragment extends Fragment {

    private EditText etSelectedDate;
    private CalendarView calendarView;

    private String selectedDate;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        initializeUI(view);
        setupTabLayoutAndViewPager();
        setupCalendarViewListener();
        setupEditTextListener();

        TabLayout tabLayout = view.findViewById(R.id.tabLayout6);
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


        return view;
    }

    // Khởi tạo các phần tử UI
    private void initializeUI(View view) {
        etSelectedDate = view.findViewById(R.id.et_selected_date);
        calendarView = view.findViewById(R.id.calendarView);
        tabLayout = view.findViewById(R.id.tabLayout6);
        viewPager = view.findViewById(R.id.viewPager);
    }

    // Cài đặt TabLayout và ViewPager
    private void setupTabLayoutAndViewPager() {
        TabAdapter tabAdapter = new TabAdapter(requireActivity());
        viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(position == 0 ? "Chi tiêu" : "Thu nhập");
        }).attach();

        viewPager.setVisibility(View.GONE); // Ẩn ViewPager ban đầu
    }

    // Cài đặt sự kiện khi chọn ngày từ CalendarView
    private void setupCalendarViewListener() {
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            selectedDate = formatDate(dayOfMonth, month, year);
            etSelectedDate.setText(selectedDate); // Hiển thị ngày đã chọn
            hideCalendarAndShowTabs();
            sendDateToFragments();
        });
    }

    // Cài đặt sự kiện khi click vào EditText để mở CalendarView
    private void setupEditTextListener() {
        etSelectedDate.setOnClickListener(v -> {
            hideTabsAndShowCalendar();
        });
    }

    // Định dạng lại ngày đã chọn
    private String formatDate(int dayOfMonth, int month, int year) {
        return dayOfMonth + "/" + (month + 1) + "/" + year;
    }

    // Ẩn CalendarView và hiển thị TabLayout cùng ViewPager
    private void hideCalendarAndShowTabs() {
        calendarView.setVisibility(View.GONE);
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
    }

    // Ẩn TabLayout và ViewPager, hiển thị CalendarView
    private void hideTabsAndShowCalendar() {
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        calendarView.setVisibility(View.VISIBLE);
    }

    // Gửi ngày đã chọn cho các fragment trong ViewPager
    private void sendDateToFragments() {
        TabAdapter adapter = (TabAdapter) viewPager.getAdapter();
        if (adapter != null) {
            adapter.setSelectedDate(selectedDate); // Cập nhật ngày đã chọn cho các fragment
        }
    }
}























//package com.example.bai1.Fragment1;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CalendarView;
//import android.widget.EditText;
//
//import androidx.fragment.app.Fragment;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.example.bai1.R;
//import com.example.bai1.adapter.TabAdapter;
//import com.google.android.material.tabs.TabLayout;
//import com.google.android.material.tabs.TabLayoutMediator;
//
//public class CalendarFragment extends Fragment {
//
//    private EditText etSelectedDate;
//    private CalendarView calendarView;
//
//    private String selectedDate;
//
//
//
//    private TabLayout tabLayout;
//    private ViewPager2 viewPager;
//
//    public CalendarFragment() {
//        // Required empty public constructor
//    }
//
//    public static CalendarFragment newInstance(String param1, String param2) {
//        CalendarFragment fragment = new CalendarFragment();
//        Bundle args = new Bundle();
//        args.putString("param1", param1);
//        args.putString("param2", param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
//
//        // Khởi tạo các phần tử UI
//        etSelectedDate = view.findViewById(R.id.et_selected_date);
//        calendarView = view.findViewById(R.id.calendarView);
//
//        tabLayout = view.findViewById(R.id.tabLayout);
//        viewPager = view.findViewById(R.id.viewPager);
//
//        TabAdapter tabAdapter = new TabAdapter(requireActivity());
//        viewPager.setAdapter(tabAdapter);
//        new TabLayoutMediator(tabLayout, viewPager,
//                (tab, position) -> {
//                    if (position == 0) {
//                        tab.setText("Chi tiêu");
//                    } else {
//                        tab.setText("Thu nhập");
//                    }
//                }).attach();
//
//        viewPager.setVisibility(View.GONE);
//
//        // Sự kiện khi chọn ngày từ CalendarView
//        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
//            // Định dạng lại ngày đã chọn
//            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
//            // Hiển thị ngày đã chọn lên EditText
//            etSelectedDate.setText(selectedDate);
//            // Ẩn CalendarView sau khi chọn ngày
//            calendarView.setVisibility(View.GONE);
//            tabLayout.setVisibility(View.VISIBLE);
//            viewPager.setVisibility(View.VISIBLE);
//
//            sendDateToFragments();
//        });
//
//        // Khi nhấn vào EditText để mở CalendarView
//        etSelectedDate.setOnClickListener(v -> {
//            viewPager.setVisibility(View.GONE);
//
//            tabLayout.setVisibility(View.GONE);
//            calendarView.setVisibility(View.VISIBLE); // Hiển thị lại CalendarView khi click vào EditText
//        });
//
//        return view;
//    }
//    private void sendDateToFragments() {
//        TabAdapter adapter = (TabAdapter) viewPager.getAdapter();
//        if (adapter != null) {
//            adapter.setSelectedDate(selectedDate);
//        }
//    }
//
//}
