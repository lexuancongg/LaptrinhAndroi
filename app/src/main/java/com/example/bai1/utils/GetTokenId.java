package com.example.bai1.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class GetTokenId {

    private static FirebaseAuth mAuth;

    // Callback interface để trả giá trị token
    public interface TokenCallback {
        void onTokenReceived(String token);
        void onFailure(Exception e);
    }

    public static void get(TokenCallback callback) {
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Lấy token mới (refresh nếu cần thiết)
            user.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                // Lấy token thành công
                                String idToken = task.getResult().getToken();
                                callback.onTokenReceived(idToken);
                            } else {
  
                                callback.onFailure(task.getException());
                            }
                        }
                    });
        } else {
            callback.onFailure(new Exception("User not logged in"));
        }
    }
}
