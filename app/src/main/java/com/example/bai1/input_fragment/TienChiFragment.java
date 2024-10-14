package com.example.bai1.input_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.bai1.Fragment1.GridViewAdapter;
import com.example.bai1.R;

public class TienChiFragment extends Fragment {
    String[] ten = {
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn","Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",
            "Xe cộ", "Tiền nhà", "Dự phòng", "Tiền ăn",

    };
    GridView gridView;
    int[] hinh = {
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,
            R.drawable.car, R.drawable.home, R.drawable.money, R.drawable.restaurant,

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tien_chi, container, false);

        // Khởi tạo GridView và adapter ở đây
        gridView = view.findViewById(R.id.gridView); // Sử dụng view đã inflate
        GridViewAdapter adapter = new GridViewAdapter(requireContext(), ten, hinh);
        gridView.setAdapter(adapter);

        return view; // Đảm bảo return view sau cùng
    }
}
