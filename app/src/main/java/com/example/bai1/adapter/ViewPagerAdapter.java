package com.example.bai1.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bai1.chitieu;
import com.example.bai1.thunhap;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final String year; // Biến lưu trữ năm

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String year) {
        super(fragmentActivity);
        this.year = year;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // Truyền năm vào chitieu Fragment
                chitieu chiTieuFragment = new chitieu();
                Bundle bundleChiTieu = new Bundle();
                bundleChiTieu.putString("year", year);
                chiTieuFragment.setArguments(bundleChiTieu);
                return chiTieuFragment;
            case 1:
                // Truyền năm vào thunhap Fragment
                thunhap thuNhapFragment = new thunhap();
                Bundle bundleThuNhap = new Bundle();
                bundleThuNhap.putString("year", year);
                thuNhapFragment.setArguments(bundleThuNhap);
                return thuNhapFragment;
            default:
                return new chitieu();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
