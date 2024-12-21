package com.example.bai1.Fragment1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bai1.R;
import com.example.bai1.input_fragment.TienChiFragment;
import com.example.bai1.input_fragment.TienThuFragment;

public class TitleTab extends Fragment {

    private TextView tvChiTieu;
    private TextView tvThuNhap;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.title_tab, container, false); // Inflate layout của fragment vào view

        initializeViews();  // Khởi tạo các view
        setUpListeners();  // Thiết lập các sự kiện nhấn cho các TextView

        displayInitialFragment(); // Hiển thị fragment Chi tiêu khi khởi tạo

        return mView; // Trả về view đã được inflate
    }

    // Khởi tạo các TextView
    private void initializeViews() {
        tvChiTieu = mView.findViewById(R.id.tv_chi_tieu);
        tvThuNhap = mView.findViewById(R.id.tv_thu_nhap);
    }

    // Thiết lập các sự kiện nhấn cho các TextView
    private void setUpListeners() {
        // Sự kiện nhấn vào "Chi tiêu"
        tvChiTieu.setOnClickListener(v -> {
            replaceFragment(new TienChiFragment());  // Thay đổi fragment hiển thị
            highlightSelectedTab(tvChiTieu, tvThuNhap);  // Đổi màu cho TextView đã chọn
        });

        // Sự kiện nhấn vào "Thu nhập"
        tvThuNhap.setOnClickListener(v -> {
            replaceFragment(new TienThuFragment());  // Thay đổi fragment hiển thị
            highlightSelectedTab(tvThuNhap, tvChiTieu);  // Đổi màu cho TextView đã chọn
        });
    }

    // Phương thức thay đổi fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.lout, fragment); // Đặt fragment vào container
        transaction.commit();
    }

    // Phương thức hiển thị fragment ban đầu (Chi tiêu)
    private void displayInitialFragment() {
        replaceFragment(new TienChiFragment());
    }

    // Phương thức thay đổi màu của các TextView khi được chọn
    private void highlightSelectedTab(TextView selectedTab, TextView unselectedTab) {
        selectedTab.setBackgroundColor(getResources().getColor(R.color.tabUnselectedTextColor));  // Màu cho TextView được chọn
        unselectedTab.setBackgroundColor(getResources().getColor(R.color.editTextBorderColor));  // Màu cho TextView không được chọn
    }
}























//package com.example.bai1.Fragment1;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.bai1.R;
//import com.example.bai1.input_fragment.TienChiFragment;
//import com.example.bai1.input_fragment.TienThuFragment;
//
///**
// * A simple {@link Fragment} subclass.
// * create an instance of this fragment.
//// */
//public class TitleTab extends Fragment {
//    private TextView tvChiTieu;
//    private TextView tvThuNhap;
//    private View mView;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate layout của Fragment vào view
//        mView = inflater.inflate(R.layout.title_tab, container, false);
//
//        // Khởi tạo các TextView
//        tvChiTieu = mView.findViewById(R.id.tv_chi_tieu);
//        tvThuNhap = mView.findViewById(R.id.tv_thu_nhap);
//
//        // Hiển thị giao diện Chi tiêu ban đầu
//        replaceFragment(new TienChiFragment());
//
//        // Xử lý sự kiện nhấn vào TextView "Chi tiêu"
//        tvChiTieu.setOnClickListener(v -> {
//            replaceFragment(new TienChiFragment()); // Hiển thị Fragment Chi tiêu
//            tvChiTieu.setBackgroundColor(getResources().getColor(R.color.tabUnselectedTextColor)); // Đổi màu khi chọn
//            tvThuNhap.setBackgroundColor(getResources().getColor(R.color.editTextBorderColor)); // Đổi lại màu cho TextView Thu nhập
//        });
//
//        // Xử lý sự kiện nhấn vào TextView "Thu nhập"
//        tvThuNhap.setOnClickListener(v -> {
//            replaceFragment(new TienThuFragment()); // Hiển thị Fragment Thu nhập
//            tvThuNhap.setBackgroundColor(getResources().getColor(R.color.tabUnselectedTextColor));
//            tvChiTieu.setBackgroundColor(getResources().getColor(R.color.editTextBorderColor));
//        });
//
//        return mView; // Trả về view đã được inflate
//    }
//
//    // Phương thức thay đổi fragment
//    private void replaceFragment(Fragment fragment) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.lout, fragment); // Đặt fragment vào container
//        transaction.commit();
//    }
//}