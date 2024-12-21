package com.example.bai1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bai1.MainActivity;
import com.example.bai1.R;
import com.example.bai1.databinding.ActivityLogginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    // theo file xml ánh xa
    private ActivityLogginBinding activityLogginBinding;
    private EditText inputUsername, inputPassword;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityLogginBinding = ActivityLogginBinding.inflate(getLayoutInflater());

        // Gắn layout với Activity
        setContentView(activityLogginBinding.getRoot());


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loggin);

        mAuth = FirebaseAuth.getInstance();
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        TextView createAccount = findViewById(R.id.createAccountId);
        Button loginButton = findViewById(R.id.buttonLoggin);

        loginButton.setOnClickListener(this::loginUser);

        createAccount.setOnClickListener(v -> {
            // Intent : đối tượng mô tả hành động muốn thực hiện
            Intent actions = new Intent(LoginActivity.this, RegisterActivity.class);
            // bắt đầu một màn hình mới
            startActivity(actions);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void loginUser(View view){

        String email = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (TextUtils.isEmpty(email)) {
            inputUsername.setError("Vui lòng nhập email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Vui lòng nhập mật khẩu");
            return;
        }
        this.signIn(email,password);

    }


    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            navigateToHome();




                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Email sent
                    }
                });
        // [END send_email_verification]
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {


    }
    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}