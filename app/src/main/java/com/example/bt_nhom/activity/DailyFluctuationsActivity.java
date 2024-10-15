package com.example.bt_nhom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class DailyFluctuationsActivity extends AppCompatActivity {
    private static Biendongsodu[] biendongsodus = new Biendongsodu[31];
    static {
        for (int i = 0; i < 31; i++) {
            int day = i + 1; // Ngày từ 1 đến 31
            int money = (int) (Math.random() * 1000 -500); // Giá trị tiền ngẫu nhiên từ 500 đến 1500
            biendongsodus[i] = new Biendongsodu(day, money, true); // Khởi tạo theo ngày
        }
    }
    private ArrayAdapter<Biendongsodu> biendongsoduArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_fluctuations);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int thang = getIntent().getIntExtra("thang", 0);
        int money = getIntent().getIntExtra("money", 0);
        if(thang!=0){
            TextView month = findViewById(R.id.MondId);
            month.setText("Tháng "+thang);
        }

        ListView listView = findViewById(R.id.lisviewId);
        biendongsoduArrayAdapter = new BiendongsoduAdapter(DailyFluctuationsActivity.this, biendongsodus);
        listView.setAdapter(biendongsoduArrayAdapter);

        TextView backOther = findViewById(R.id.backId);
        backOther.setOnClickListener(v -> {

            Intent actions = new Intent(DailyFluctuationsActivity.this , MonthlyFluctuationsActivity.class);
            startActivity(actions);
        });

    }
}
