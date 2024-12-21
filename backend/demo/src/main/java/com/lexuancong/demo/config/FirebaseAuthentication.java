package com.lexuancong.demo.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import com.google.firebase.auth.FirebaseToken;

public class FirebaseAuthentication extends AbstractAuthenticationToken {

    private final FirebaseToken firebaseToken;

    public FirebaseAuthentication(FirebaseToken firebaseToken) {
        super(null);
        this.firebaseToken = firebaseToken;
        setAuthenticated(true);
    }

    public FirebaseToken getFirebaseToken() {
        return firebaseToken;
    }

    @Override
    public Object getCredentials() {
        return firebaseToken.getUid();
    }

    @Override
    public Object getPrincipal() {
        return firebaseToken.getEmail();
    }
}
