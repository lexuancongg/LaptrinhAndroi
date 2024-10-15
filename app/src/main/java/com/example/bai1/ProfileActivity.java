package com.example.bai1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        linearLayout = (LinearLayout) findViewById(R.id.trove5);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,CaiDatKhacActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView backProfile = findViewById(R.id.titleText1);
        backProfile.setOnClickListener(v->{
            Intent actions = new Intent(ProfileActivity.this,CaiDatKhacActivity.class);
            startActivity(actions);
            finish();
        });
    }
}