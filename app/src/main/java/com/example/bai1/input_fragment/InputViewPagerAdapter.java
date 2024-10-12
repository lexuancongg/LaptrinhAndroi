package com.example.bai1.input_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class InputViewPagerAdapter extends FragmentStateAdapter {
    public InputViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TienChiFragment();
            case 1:
                return new TienThuFragment();
            default:
                return new TienChiFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
