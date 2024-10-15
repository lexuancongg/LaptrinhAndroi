package com.example.bt_nhom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_nhom.R;
import com.example.bt_nhom.adapter.BiendongsoduAdapter;
import com.example.bt_nhom.dto.Biendongsodu;

import java.util.Random;

public class MonthlyFluctuationsActivity extends AppCompatActivity {

    private static Biendongsodu[] biendongsodus = new Biendongsodu[12];

    static {
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int month = i + 1; // Tháng từ 1 đến 12
            int money = -1000 + random.nextInt(2000); // Random số tiền từ 1000 đến 2999
            biendongsodus[i] = new Biendongsodu(month, money);
        }
    }

    private ArrayAdapter<Biendongsodu> biendongsoduArrayAdapter;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_monthly_fluctuations);

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });

    ListView listView = findViewById(R.id.lisviewId);

    // Khôi phục trạng thái nếu có
    if (savedInstanceState != null) {
        // Khôi phục danh sách
        Biendongsodu[] savedData = (Biendongsodu[]) savedInstanceState.getSerializable("biendongsodus");
        if (savedData != null) {
            biendongsodus = savedData; // Gán lại dữ liệu đã khôi phục
        }
    }

    // Thiết lập adapter với dữ liệu ban đầu
    biendongsoduArrayAdapter = new BiendongsoduAdapter(MonthlyFluctuationsActivity.this, biendongsodus);
    listView.setAdapter(biendongsoduArrayAdapter);

    Button btn_see = findViewById(R.id.seeId);
    btn_see.setOnClickListener(v -> {
        // Thiết lập adapter cho ListView khi nút "See" được nhấn (không cần thiết lập lại nếu đã thiết lập trước đó)
        // biendongsoduArrayAdapter = new BiendongsoduAdapter(MonthlyFluctuationsActivity.this, biendongsodus); // không cần thiết lập lại
        listView.setAdapter(biendongsoduArrayAdapter);
    });

    TextView backOther = findViewById(R.id.backOtherId);
    backOther.setOnClickListener(v -> {
        Intent actions = new Intent(MonthlyFluctuationsActivity.this, LoginActivity.class);
        startActivity(actions);
    });
}

    // Lưu trạng thái khi Activity bị tạm dừng
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Lưu dữ liệu của biendongsodus
        outState.putSerializable("biendongsodus", biendongsodus);
    }




}
