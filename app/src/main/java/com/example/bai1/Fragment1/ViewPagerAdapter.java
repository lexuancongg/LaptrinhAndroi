package com.example.bai1.Fragment1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter  extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TitleTab();
            case 1:
                return new CalendarFragment();
            case 2:
                return new AnotherFragment();
            default:
                return new TitleTab();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
