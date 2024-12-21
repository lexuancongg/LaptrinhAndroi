package com.example.bai1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private TextView tvGroupName;
    private LinearLayout memberListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Ánh xạ các view
        linearLayout = findViewById(R.id.trove5);
        tvGroupName = findViewById(R.id.tv_group_name);
        memberListContainer = findViewById(R.id.member_list_container);

        // Hiển thị thông tin nhóm
        tvGroupName.setText("Tên nhóm: Nhóm 20");

        // Hiển thị danh sách thành viên
        List<String> members = new ArrayList<>();
        members.add("Lê Xuân Công");
        members.add("Nguyễn Thị Thu");
        members.add("Trần Văn A");

        displayMemberList(members);

        // Gắn sự kiện click cho trove5
        linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void displayMemberList(List<String> members) {
        memberListContainer.removeAllViews(); // Xóa các thành phần cũ nếu có

        for (String member : members) {
            TextView textView = new TextView(this);
            textView.setText(member);
            textView.setTextSize(16f);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setPadding(8, 8, 8, 8);
            memberListContainer.addView(textView);
        }
    }
}
