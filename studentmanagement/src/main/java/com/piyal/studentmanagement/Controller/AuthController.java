package com.piyal.studentmanagement.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piyal.studentmanagement.DTO.AuthRequestDTO;
import com.piyal.studentmanagement.Model.User;
import com.piyal.studentmanagement.Repository.UserRepository;
import com.piyal.studentmanagement.SecurityConfig.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  UserRepository userRepo;
  PasswordEncoder passwordEncoder;
  AuthenticationManager authManager;
  JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody User user) {
    if (userRepo.findByUsername(user.getUsername()).isPresent()) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
    return ResponseEntity.ok("User registered");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody AuthRequestDTO authRequest) {
    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

    User user = userRepo.findByUsername(authRequest.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    return ResponseEntity.ok(token);
  }
}
