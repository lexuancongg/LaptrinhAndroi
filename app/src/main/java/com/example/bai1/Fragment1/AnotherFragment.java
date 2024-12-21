
    package com.example.bai1.Fragment1;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import com.example.bai1.ActivityReportList;
    import com.example.bai1.ProfileActivity;
    import com.example.bai1.R;

    public class AnotherFragment extends Fragment {

        LinearLayout thongtincanhan ,liner_history;
        LinearLayout linearLayout1;
        LinearLayout linearLayout3;
        LinearLayout linearLayout4;
        TextView textView;

        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.activity_fragment_khac, container, false);
            thongtincanhan = (LinearLayout)view.findViewById(R.id.thongtincanhan);
            thongtincanhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(requireContext(), ProfileActivity.class);
                    startActivity(intent);
                }
            });

//            linearLayout1 = (LinearLayout)view.findViewById(R.id.baocaotrongnam);
//            linearLayout1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent1 = new Intent(requireContext(), ActivityReportList.class);
//                    startActivity(intent1);
//                }
//            });
//            linearLayout3 = (LinearLayout)view.findViewById(R.id.quanlydanhmuc);
//            linearLayout3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent2 = new Intent(requireContext(), QuanLyDanhMuc.class);
//                    startActivity(intent2);
//                }
//            });
//            LinearLayout baocaothaydoisodu = view.findViewById(R.id.baocaothaydoisodu);
//            baocaothaydoisodu.setOnClickListener(v ->{
//
//            });
//            linearLayout4 = (LinearLayout)view.findViewById(R.id.premium);
//            linearLayout4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent4 = new Intent(requireContext(), Activity_tiente.class);
//                    startActivity(intent4);
//                }
//            });

            liner_history = (LinearLayout)view.findViewById(R.id.liner_history);
            liner_history.setOnClickListener(v -> {
                //Intent intent4 = new Intent(requireContext(), HistoryActivity.class);
//                startActivity(intent4);

            });
            textView = view.findViewById(R.id.helpText);
            textView.setOnClickListener(v -> {
//
            });
        return view;
        }
    }