package com.example.bt_nhom;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MonthlyFluctuationsActivity extends AppCompatActivity {
    private static Biendongsodu[] biendongsodus = new Biendongsodu[10];
    static {
        biendongsodus[0] = new Biendongsodu(1, 1000);  // Tháng 1, tiền 1000
        biendongsodus[1] = new Biendongsodu(2, 2000);  // Tháng 2, tiền 2000
        biendongsodus[2] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[3] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[4] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[5] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[6] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[7] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[8] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
        biendongsodus[9] = new Biendongsodu(3, 1500);  // Tháng 3, tiền 1500
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
    }
}
