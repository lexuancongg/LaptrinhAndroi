package com.example.bai1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bai1.MainActivity;
import com.example.bai1.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loggin);


        TextView createAccount = findViewById(R.id.createAccountId);
        createAccount.setOnClickListener(v -> {
            // Intent : đối tượng mô tả hành động muốn thực hiện
            Intent actions = new Intent(LoginActivity.this, RegisterActivity.class);
            // bắt đầu một màn hình mới
            startActivity(actions);
        });
        Button logign = findViewById(R.id.buttonLoggin);
        logign.setOnClickListener(view->{
            Intent action = new Intent(LoginActivity.this, MainActivity.class);
            // bắt đầu một màn hình mới
            startActivity(action);
        });
    }
}