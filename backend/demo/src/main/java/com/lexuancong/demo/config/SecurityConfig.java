package com.lexuancong.demo.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    public SecurityConfig(FirebaseAuthenticationFilter firebaseAuthenticationFilter) {
        this.firebaseAuthenticationFilter = firebaseAuthenticationFilter;
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/test").permitAll()
                            .requestMatchers("/category/**").permitAll()
                            .requestMatchers("/category/create").permitAll()
                            .anyRequest().authenticated())  // Các request còn lại cần phải xác thực
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))  // Cấu hình OAuth2 với JWT
                    .addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Thêm FirebaseAuthenticationFilter
                    .build();
        }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return token -> {
//            try {
//                System.out.println("this is token decode");
//                // Xác thực token với Firebase Admin SDK
//                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
//
//                // Chuyển đổi FirebaseToken thành Jwt (nếu cần)
//                return Jwt.withTokenValue(token)
//                        .header("alg", "RS256") // Hoặc các thông tin header khác nếu cần
//                        .claim("sub", decodedToken.getUid())
//                        .claim("email", decodedToken.getEmail())
//                        .build();
//
//            } catch (FirebaseAuthException e) {
//                throw new JwtException("Invalid token", e);
//            }
//        };
//    }

}


