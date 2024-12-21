package com.example.bai1.Fragment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bai1.CaiDatKhacActivity;
import com.example.bai1.MainActivity;
import com.example.bai1.R;

public class Activity_tiente extends AppCompatActivity {
   ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tiente);

        imageView = findViewById(R.id.tv);
        imageView.setOnClickListener(view -> {
            Intent intent3 = new Intent(Activity_tiente.this, MainActivity.class);
            startActivity(intent3);
        });
    }
}