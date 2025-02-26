package com.jvmdevelop.mvp.controller;

import com.jvmdevelop.mvp.dto.UserDto;
import com.jvmdevelop.mvp.model.User;
import com.jvmdevelop.mvp.service.UserService;
import com.jvmdevelop.mvp.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        userService.add(User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(hashPassword)
                .description(user.getDescription())
                .role("ROLE_USER")
                .avatarUrl(user.getAvatarUrl())
                .build());

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return JwtUtil.generateToken(userDetails);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return JwtUtil.generateToken(userDetails);
    }

}
