package com.sample.angular.controller;

import com.sample.angular.exception_handling.UserResponse;
import com.sample.angular.model.RegisterUserRequest;
import com.sample.angular.model.Role;
import com.sample.angular.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserRequest registerUserRequest){
        registerUserRequest.setRole(Role.USER);
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<UserResponse> adminRegister(@RequestBody RegisterUserRequest resRegisterUserRequest){
        UserResponse userResponse=userService.registerUser(resRegisterUserRequest);
        return ResponseEntity.ok(userResponse);
    }
}
