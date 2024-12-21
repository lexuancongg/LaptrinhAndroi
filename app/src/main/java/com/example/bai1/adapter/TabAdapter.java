package com.example.bai1.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bai1.Fragment1.ExpenseFragment;
import com.example.bai1.Fragment1.IncomeFragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bai1.Fragment1.ExpenseFragment;
import com.example.bai1.Fragment1.IncomeFragment;

import java.util.HashMap;
import java.util.Map;

public class TabAdapter extends FragmentStateAdapter {

    private final Map<Integer, Fragment> fragmentMap = new HashMap<>(); // Lưu trữ các Fragment
    private String selectedDate;

    public TabAdapter(FragmentActivity fa) {
        super(fa);
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;

        // Gửi ngày mới đến từng Fragment nếu đã tạo
        for (Fragment fragment : fragmentMap.values()) {
            if (fragment instanceof ExpenseFragment) {
                ((ExpenseFragment) fragment).updateDate(selectedDate);
            } else if (fragment instanceof IncomeFragment) {
                ((IncomeFragment) fragment).updateDate(selectedDate);
            }
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new ExpenseFragment();
        } else {
            fragment = new IncomeFragment();
        }

        // Lưu trữ Fragment đã tạo
        fragmentMap.put(position, fragment);

        // Gửi ngày nếu đã có sẵn
        if (selectedDate != null) {
            if (fragment instanceof ExpenseFragment) {
                ((ExpenseFragment) fragment).updateDate(selectedDate);
            } else if (fragment instanceof IncomeFragment) {
                ((IncomeFragment) fragment).updateDate(selectedDate);
            }
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public Fragment getFragment(int position) {
        return fragmentMap.get(position);
    }
}


//public class TabAdapter extends FragmentStateAdapter {
//
//    private String selectedDate; // Biến lưu ngày đã chọn
//
//    public TabAdapter(FragmentActivity fa) {
//        super(fa);
//    }
//
//    public void setSelectedDate(String selectedDate) {
//        this.selectedDate = selectedDate;
//        notifyDataSetChanged(); // Thông báo dữ liệu thay đổi
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        if (position == 0) {
//            ExpenseFragment fragment = new ExpenseFragment();
//            if (selectedDate != null) {
//                Bundle bundle = new Bundle();
//                bundle.putString("selectedDate", selectedDate);
//                fragment.setArguments(bundle);
//            }
//            return fragment;
//        } else {
//            IncomeFragment fragment = new IncomeFragment();
//            if (selectedDate != null) {
//                Bundle bundle = new Bundle();
//                bundle.putString("selectedDate", selectedDate);
//                fragment.setArguments(bundle);
//            }
//            return fragment;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 2; // Hai tab: Chi tiêu và Thu nhập
//    }
//}
