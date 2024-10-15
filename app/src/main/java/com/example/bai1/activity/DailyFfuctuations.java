package com.example.bai1.activity;

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

import com.example.bai1.R;
import com.example.bai1.adapter.BiendongsoduAdapter;
import com.example.bai1.dto.Biendongsodu;

public class DailyFfuctuations extends AppCompatActivity {
    private static Biendongsodu[] biendongsodus = new Biendongsodu[31];
    static {
        for (int i = 0; i < 31; i++) {
            int day = i + 1; // Ngày từ 1 đến 31
            int money = (int) (Math.random() * 1000 - 500); // Giá trị tiền ngẫu nhiên từ 500 đến 1500
            biendongsodus[i] = new Biendongsodu(day, money, true); // Khởi tạo theo ngày
        }
    }

    private ArrayAdapter<Biendongsodu> biendongsoduArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_fluctuations);
        TextView month = findViewById(R.id.MondId);
        int thang = getIntent().getIntExtra("thang",0);
        if(thang!=0){
            month.setText("Tháng "+thang);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.lisviewId);
        biendongsoduArrayAdapter = new BiendongsoduAdapter(DailyFfuctuations.this, biendongsodus);
        listView.setAdapter(biendongsoduArrayAdapter);
        TextView back = findViewById(R.id.backId);
        back.setOnClickListener(v->{
            Intent actions = new Intent(DailyFfuctuations.this,MonthlyFluctuationsActivity.class);
            startActivity(actions);
        });
    }
}
