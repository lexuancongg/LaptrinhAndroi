package com.example.bai1.Fragment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.bai1.R;
import com.example.bai1.input_fragment.InputViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    private TableLayout tableLayout;
    private ViewPager2 viewPager2;
    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_1, container, false);
        viewPager2 = mView.findViewById(R.id.input_viewpager);

        InputViewPagerAdapter adapter = new InputViewPagerAdapter(requireActivity());
        viewPager2.setAdapter(adapter);

        TabLayout tabLayout = mView.findViewById(R.id.tab_layout);

        // Kết nối TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tiền Chi");
                    break;
                case 1:
                    tab.setText("Tiền thu");
                    break;
            }
        }).attach();
        return mView;
    }
}