package com.example.bai1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bai1.CaiDatKhacActivity;
import com.example.bai1.MainActivity;
import com.example.bai1.R;
import com.example.bai1.adapter.BiendongsoduAdapter;
import com.example.bai1.dto.Biendongsodu;

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
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monthly_fluctuations);

        tv = (TextView)findViewById(R.id.backOtherId);

        ListView listView = findViewById(R.id.lisviewId);
        biendongsoduArrayAdapter = new BiendongsoduAdapter(MonthlyFluctuationsActivity.this, biendongsodus);
        listView.setAdapter(biendongsoduArrayAdapter);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trở về MainActivity và hiển thị Fragment thứ 3 (AnotherFragment)
                Intent intent = new Intent(MonthlyFluctuationsActivity.this, MainActivity.class);
                intent.putExtra("fragmentToShow", 2); // Fragment thứ 3 (AnotherFragment)
                startActivity(intent);
                finish();
            }
        });

    }
}
