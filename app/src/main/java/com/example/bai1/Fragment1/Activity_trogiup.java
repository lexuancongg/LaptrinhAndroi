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
import com.example.bai1.R;

public class Activity_trogiup extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trogiup);
        imageView = findViewById(R.id.trovee);
        imageView.setOnClickListener(v -> {
            Intent intent4 = new Intent(Activity_trogiup.this, AnotherFragment.class);
            finish();
        });
    }
}