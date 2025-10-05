package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.dto.UserDto;
import io.app.model.Skill;
import io.app.model.User;
import io.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserDto profile(@RequestHeader("Authorization") String token){
        log.info(token);
        return service.profile(token);
    }


    /*
    * Update Skills of User
    */
    @PatchMapping("/skill")
    public ApiResponse updateSkills(@RequestBody Set<Skill> skills,
                                    @RequestHeader("Authorization") String token){
        return service.updateSkills(skills,token);
    }

}
