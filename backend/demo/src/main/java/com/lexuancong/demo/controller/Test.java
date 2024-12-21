package com.lexuancong.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/example")
    public ResponseEntity<String> test(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token missing or invalid");
        }
        System.out.println("this is name");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok("Token received successfully");
    }
}
