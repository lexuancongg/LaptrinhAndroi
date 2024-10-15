    package com.example.bai1.Fragment1;

    import android.content.Intent;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;

    import com.example.bai1.CaiDatKhacActivity;
    import com.example.bai1.R;

    /**
     * A simple {@link Fragment} subclass.
     * create an instance of this fragment.
     */
    public class AnotherFragment extends Fragment {

        LinearLayout linearLayout;
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
        return view;
        }
    }