package io.app.service;

import io.app.dto.ApiResponse;
import io.app.model.Skill;

import java.util.List;
import java.util.Set;

public interface SkillsService {
    public ApiResponse createSkills(Skill skill);
    public ApiResponse createSkills(List<Skill> skills);
    public ApiResponse updateSkills(List<Skill> skills);
    public Set<Skill> getSkills(String query);
}
