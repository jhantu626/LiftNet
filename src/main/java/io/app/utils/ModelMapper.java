package io.app.utils;

import io.app.dto.UserDto;
import io.app.model.User;
import org.springframework.stereotype.Service;

@Service
public class ModelMapper {
    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setResumeUrl(user.getResumeUrl());
        return userDto;
    }
}
