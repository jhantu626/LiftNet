package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.model.Skill;
import io.app.service.impl.SkillsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/skills")
public class SkillsController {
    private final SkillsServiceImpl service;
    public SkillsController(SkillsServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse addSkill(@RequestBody Skill skill) {
        return service.createSkills(skill);
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse addSkill(@RequestBody List<Skill> skill) {
        return service.createSkills(skill);
    }

    @GetMapping
    public ResponseEntity<Collection<Skill>> getSkills(@RequestParam(required = false) String query){
        return ResponseEntity.ok(service.getSkills(query));
    }

}
