package com.example.bai1.ThemDanhMuc_Tab_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.bai1.Fragment1.GridViewAdapter;
import com.example.bai1.R;

public class TienChiDanhMucFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tienchi_themdanhmuc, container, false);

        return view; // Đảm bảo return view sau cùng

    }

}
