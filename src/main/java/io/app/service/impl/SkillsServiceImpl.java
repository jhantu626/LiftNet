package io.app.service.impl;

import io.app.dto.ApiResponse;
import io.app.model.Skill;
import io.app.repository.SkillRepository;
import io.app.service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SkillsServiceImpl implements SkillsService {
    private final SkillRepository repository;

    @Override
    public ApiResponse createSkills(Skill skills) {
        repository.save(skills);
        return ApiResponse.builder().status(true).message("Successfully Created").build();
    }

    @Override
    public ApiResponse createSkills(List<Skill> skills) {
        repository.saveAll(skills);
        return ApiResponse.builder().status(true).message("Successfully Created").build();
    }

    @Override
    public ApiResponse updateSkills(List<Skill> skills) {
        return null;
    }

    @Override
    public Set<Skill> getSkills(String query) {
        Set<Skill> skills=repository.findByNameContaining(query);
        return skills;
    }
}
