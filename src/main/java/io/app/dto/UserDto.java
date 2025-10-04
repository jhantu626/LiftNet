package io.app.dto;

import io.app.model.Location;
import io.app.model.Role;
import io.app.model.Skill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String resumeUrl;
    private Set<Skill> skills=new HashSet<>();
    private Location location;
}