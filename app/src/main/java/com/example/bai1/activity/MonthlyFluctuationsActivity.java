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
        biendongsoduArrayAdapter = new BiendongsoduAdapter(MonthlyFluctuationsActivity.this, biendongsodus);
        listView.setAdapter(biendongsoduArrayAdapter);
        TextView backOther  = findViewById(R.id.backOtherId);
        backOther.setOnClickListener(v->{

        });
    }
}
