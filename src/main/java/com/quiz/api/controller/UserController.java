package com.quiz.api.controller;

import com.quiz.api.Annotation.Permission;
import com.quiz.api.enums.Role;
import com.quiz.api.request.LoginRequest;
import com.quiz.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Permission(roles = {Role.Admin,Role.Public})
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @NonNull LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @Permission(roles = {Role.Admin,Role.Public})
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(){
        return userService.logout();
    }

}
