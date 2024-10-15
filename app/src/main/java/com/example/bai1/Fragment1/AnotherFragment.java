    package com.example.bai1.Fragment1;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;

    import com.example.bai1.ActivityReportList;
    import com.example.bai1.CaiDatKhacActivity;
    import com.example.bai1.R;

    /**
     * A simple {@link Fragment} subclass.
     * create an instance of this fragment.
     */
    public class AnotherFragment extends Fragment {

        LinearLayout linearLayout;
        LinearLayout linearLayout1;
        LinearLayout linearLayout3;
        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.activity_fragment_khac, container, false);
            linearLayout = (LinearLayout)view.findViewById(R.id.settingother);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(requireContext(), CaiDatKhacActivity.class);
                    startActivity(intent);
                }
            });

            linearLayout1 = (LinearLayout)view.findViewById(R.id.baocaotrongnam);
            linearLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(requireContext(), ActivityReportList.class);
                    startActivity(intent1);
                }
            });
            linearLayout3 = (LinearLayout)view.findViewById(R.id.timkiemgiaodich);
            linearLayout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(requireContext(), ActivitySearch.class);
                    startActivity(intent2);
                }
            });

        return view;
        }
    }