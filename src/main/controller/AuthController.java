package com.sweetshop.controller;

import com.sweetshop.model.User;
import com.sweetshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        User user = authService.register(req.get("username"), req.get("email"), req.get("password"));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String token = authService.login(req.get("email"), req.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
