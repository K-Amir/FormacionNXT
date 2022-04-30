package com.example.springcrud.demo.Person.Application;

import com.example.springcrud.demo.Person.Application.Dtos.InputAuthDto;
import com.example.springcrud.demo.Security.JwtUtil;
import com.example.springcrud.demo.Security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("login")
    public ResponseEntity<?> loginPerson(@RequestBody InputAuthDto inputAuthDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputAuthDto.getEmail(), inputAuthDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Bad credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(inputAuthDto.getEmail());
        String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(jwtToken);
    }

}
