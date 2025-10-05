package io.app.service.impl;

import io.app.dto.ApiResponse;
import io.app.dto.UserDto;
import io.app.exception.ResourceNotFoundException;
import io.app.model.Skill;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.service.JwtService;
import io.app.service.UserService;
import io.app.utils.ModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @Override
    public UserDto profile(String token) {
        String username= jwtService.extractUsername(token.substring(7));
        User user=repository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        return modelMapper.mapToUserDto(user);
    }

    @Override
    @Transactional
    public ApiResponse updateSkills(Set<Skill> skills,String token) {
        String username= jwtService.extractUsername(token.substring(7));
        log.info("username is: {}",username);
        User user=repository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Credentials"));
        user.setSkills(skills);
        return ApiResponse.builder()
                .message("Updated Successfully!")
                .status(true)
                .build();
    }
}
