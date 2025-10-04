package io.app.service.impl;

import io.app.dto.ApiResponse;
import io.app.dto.AuthResponse;
import io.app.exception.NoLongerAvailableException;
import io.app.exception.NotMatchingException;
import io.app.exception.ResourceNotFoundException;
import io.app.model.Role;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.service.JwtService;
import io.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;


    @Override
    public ApiResponse login(String email) {
        Optional<User> user = repository.findByEmail(email);
        String otp=String.valueOf((int)(Math.random()*10000));
        LocalDateTime expirationTime=LocalDateTime.now().plusMinutes(5);
        User users=null;
        if (user.isPresent()){
            users=user.get();
            users.setOtp(otp);
            users.setOtpExpiration(expirationTime);
        }else {
            users=User.builder()
                    .email(email)
                    .otp(otp)
                    .otpExpiration(expirationTime)
                    .role(Role.USER)
                    .build();
        }
        repository.save(users);
        return ApiResponse.builder()
                .message("Otp Sent Successfully!")
                .status(true)
                .build();
    }

    @Override
    public AuthResponse validateOtp(String email, String otp) {
        User user=repository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("invalid user email"));
        if (!user.getOtp().equals(otp)){
            throw new NotMatchingException("Invalid otp!");
        }
        if (user.getOtpExpiration()!=null && LocalDateTime.now().isAfter(user.getOtpExpiration())){
            throw new NoLongerAvailableException("Otp No Longer Available");
        }
        String token=jwtService.generateToken(user);
        return AuthResponse.builder()
                .status(true)
                .token(token)
                .build();
    }
}
