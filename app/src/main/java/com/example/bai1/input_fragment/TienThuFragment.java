package com.example.bai1.input_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.bai1.Fragment1.GridViewAdapter;
import com.example.bai1.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TienThuFragment extends Fragment {
    String[] ten_thu = {
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn","Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",

    };
    GridView gridView;
    int[] hinh_thu = {
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,

    };

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tien_thu, container, false);

        gridView = view.findViewById(R.id.gridView_thu); // Sử dụng view đã inflate
        GridViewAdapter adapter = new GridViewAdapter(requireContext(), ten_thu, hinh_thu);
        gridView.setAdapter(adapter);

        return view;
    }
}