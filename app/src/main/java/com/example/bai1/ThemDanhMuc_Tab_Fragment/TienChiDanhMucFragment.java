package com.example.bai1.ThemDanhMuc_Tab_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.bai1.Add_items_ThemDanhMuc;
import com.example.bai1.R;

public class TienChiDanhMucFragment extends Fragment {
    ImageButton imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tienchi_themdanhmuc, container, false);

        // Tìm LinearLayout theo ID
        LinearLayout layoutThemDM = view.findViewById(R.id.id_themDM_2);

        // Thiết lập OnClickListener
        layoutThemDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity mới
                Intent intent = new Intent(requireContext(), Add_items_ThemDanhMuc.class); // NewActivity là Activity mà bạn muốn chuyển đến
                startActivity(intent);
            }
        });

        return view;

    }

}
