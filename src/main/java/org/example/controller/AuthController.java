package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.auth.LoginDTO;
import org.example.dto.auth.RegistrationDTO;
import org.example.dto.profile.ProfileDTO;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDTO registrationDTO) {
        String response = authService.registration(registrationDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/verification/{userId}")
    public ResponseEntity<String> verification(@PathVariable("userId") Integer userId) {
        String body = authService.authorizationVerification(userId);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/registration/resend/{email}")
    public ResponseEntity<String> registrationResend(@PathVariable("email") String email) {
        String body = authService.registrationResend(email);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> registrationByEmail(@Valid @RequestBody LoginDTO dto) {
        ProfileDTO body = authService.login(dto);
        return ResponseEntity.ok().body(body);
    }
}
