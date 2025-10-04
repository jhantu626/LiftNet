package io.app.repository;

import io.app.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    public Set<Skill> findByNameContaining(String name);
}
