package com.example.bai1.ThemDanhMuc_Tab_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bai1.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ScrollChiFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scroll_chi, container, false);
    }
}