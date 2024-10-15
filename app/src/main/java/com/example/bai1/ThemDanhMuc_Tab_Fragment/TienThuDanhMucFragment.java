package com.example.bai1.ThemDanhMuc_Tab_Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.bai1.Add_items_ThemDanhMuc;
import com.example.bai1.Fragment1.GridViewAdapter;
import com.example.bai1.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TienThuDanhMucFragment extends Fragment {
    LinearLayout linearLayout;
    ImageButton imgBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tienthu_themdanhmuc, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.them_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), Add_items_ThemDanhMuc.class);
                startActivity(intent);
            }
        });

        return view;
    }
}