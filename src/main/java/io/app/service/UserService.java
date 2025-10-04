package io.app.service;

import io.app.dto.ApiResponse;
import io.app.dto.UserDto;
import io.app.model.Skill;
import io.app.model.User;

import java.util.Set;

public interface UserService {
    public UserDto profile(String token);
    public ApiResponse updateSkills(Set<Skill> skills);
}
