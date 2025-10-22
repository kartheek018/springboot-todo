package com.sample.angular.controller;
import com.sample.angular.model.AuthRequest;
import com.sample.angular.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    AuthenticationManager authenticationManager;

    @Autowired
    jwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
            System.out.println(jwtUtil.generateToken(authRequest.getUsername()));
            return jwtUtil.generateToken(authRequest.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}