package com.piyal.studentmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.piyal.studentmanagement.DTO.RegisterRequestDTO;
import com.piyal.studentmanagement.Model.Role;
import com.piyal.studentmanagement.Model.User;
import com.piyal.studentmanagement.Repository.UserRepository;
import com.piyal.studentmanagement.SecurityConfig.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  @Autowired
  UserRepository userRepo;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
    if (userRepo.findByUsername(request.getUsername()).isPresent()) {
      return ResponseEntity.badRequest().body("Username already exists");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole() != null ? request.getRole() : Role.USER);

    userRepo.save(user);
    return ResponseEntity.ok("User registered as " + user.getRole());
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
