package com.sample.angular.controller;
import com.sample.angular.model.AuthRequest;
import com.sample.angular.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    AuthenticationManager authenticationManager;

    @Autowired
    jwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest){
        // Let authentication exceptions bubble up to be handled centrally
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );

        String token = jwtUtil.generateToken(authRequest.getUsername());
        Map<String,String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}