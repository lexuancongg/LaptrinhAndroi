package com.example.bai1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai1.Fragment1.Activity_tiente;
import com.example.bai1.activity.LoginActivity;

public class CaiDatKhacActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    LinearLayout linearLayout4;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_khac);
        ImageView trove2 = findViewById(R.id.trove2);
        trove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout = findViewById(R.id.profile);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CaiDatKhacActivity.this, ProfileActivity.class);
                startActivity(intent1);
            }
        });
        linearLayout2 = findViewById(R.id.themdanhmuc);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CaiDatKhacActivity.this, ThemDanhMuc.class);
                startActivity(intent2);
            }
        });
        linearLayout3 = findViewById(R.id.thaydoitiente);
        linearLayout3.setOnClickListener(view -> {
            Intent intent3 = new Intent(CaiDatKhacActivity.this, Activity_tiente.class);
            startActivity(intent3);
        });
        linearLayout4 = findViewById(R.id.dangxuat);
        linearLayout4.setOnClickListener(view -> {
            Intent intent4= new Intent(CaiDatKhacActivity.this, LoginActivity.class);
            startActivity(intent4);
        });
    }
}
