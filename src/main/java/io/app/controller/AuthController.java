package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.dto.AuthResponse;
import io.app.repository.UserRepository;
import io.app.service.impl.AuthServiceImpl;
import jakarta.servlet.ServletConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final AuthServiceImpl authService;
    private final ServletConfig servletConfig;

    @PostMapping
    public ResponseEntity<ApiResponse> login(@RequestParam("email") String email){
        return ResponseEntity.ok(authService.login(email));
    }

    @PostMapping("/validate-otp")
    public AuthResponse verifyOtp(@RequestParam String email,
                                  @RequestParam String otp){
        return authService.validateOtp(email,otp);
    }

}
