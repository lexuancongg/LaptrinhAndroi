package com.example.bai1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai1.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding; // View Binding cho Activity
    private FirebaseAuth mAuth;             // Firebase Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gắn layout qua View Binding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Khởi tạo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Xử lý sự kiện cho TextView "Have an account?"
        binding.haveAccountI.setOnClickListener(v -> navigateToLogin());

        // Xử lý sự kiện cho nút đăng ký
        binding.buttondangky.setOnClickListener(v -> registerUser());
    }

    /**
     * Phương thức chuyển hướng đến màn hình đăng nhập.
     */
    private void navigateToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Phương thức đăng ký tài khoản Firebase.
     */
    private void registerUser() {
        // Lấy dữ liệu từ các trường nhập liệu
        String username = binding.inputUsername.getText().toString().trim();
        String email = binding.inputEmail.getText().toString().trim();
        String password = binding.inputPassword.getText().toString().trim();
        String confirmPassword = binding.inputConfirmPassword.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (TextUtils.isEmpty(username)) {
            binding.inputUsername.setError("Vui lòng nhập tên đăng nhập");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            binding.inputEmail.setError("Vui lòng nhập email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            binding.inputPassword.setError("Vui lòng nhập mật khẩu");
            return;
        }
        if (!password.equals(confirmPassword)) {
            binding.inputConfirmPassword.setError("Mật khẩu không khớp");
            return;
        }
        if (password.length() < 6) {
            binding.inputPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            return;
        }

        // Đăng ký tài khoản Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if(user!= null){
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build();
                            user.updateProfile(profileUpdates);
                        }




                        sendVerificationEmail();
                    } else {
                        Toast.makeText(RegisterActivity.this,
                                "Đăng ký thất bại: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * Phương thức gửi email xác minh.
     */
    private void sendVerificationEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,
                                    "Đăng ký thành công! Vui lòng xác minh email.",
                                    Toast.LENGTH_LONG).show();
                            navigateToLogin(); // Chuyển về màn hình đăng nhập
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Lỗi khi gửi email xác minh: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}
