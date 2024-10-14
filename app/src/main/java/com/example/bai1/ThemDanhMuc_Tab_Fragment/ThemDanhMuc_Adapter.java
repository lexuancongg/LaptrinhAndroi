package com.example.bai1.ThemDanhMuc_Tab_Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bai1.input_fragment.TienChiFragment;
import com.example.bai1.input_fragment.TienThuFragment;

public class ThemDanhMuc_Adapter extends FragmentStateAdapter {
    public ThemDanhMuc_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TienChiDanhMucFragment();
            case 1:
                return new TienThuDanhMucFragment();
            default:
                return new TienChiDanhMucFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
