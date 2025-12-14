package com.sample.angular.service;

import com.sample.angular.exception_handling.UserResponse;
import com.sample.angular.model.RegisterUserRequest;
import com.sample.angular.model.Users;
import com.sample.angular.repository.userRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final userRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(userRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest userRequest){
        // check if Users already existed or not
        // encode the password, before saving.
        // save Users
        if(usersRepository.findByUsername(userRequest.getUsername()).isPresent()){
            throw new RuntimeException("User already existed");
        }

        Users user=new Users();
        user.setUsername(userRequest.getUsername());
        user.setRole(userRequest.getRole());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Users savedUser = usersRepository.save(user);
        return new UserResponse(savedUser.getId(),savedUser.getUsername(),savedUser.getRole().name());
    }
}