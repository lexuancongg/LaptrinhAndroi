package com.lexuancong.demo.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@WebFilter("/*")
@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthenticationFilter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);
                SecurityContextHolder.getContext().setAuthentication(new FirebaseAuthentication(decodedToken));
            } catch (Exception e) {
                System.out.printf("thất bại nhé");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
