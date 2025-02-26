package com.jvmdevelop.mvp.controller;

import com.jvmdevelop.mvp.model.User;
import com.jvmdevelop.mvp.model.UserDetailsImpl;
import com.jvmdevelop.mvp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public String getCurrentSession(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUsername();
    }

    @GetMapping("/getUserByLogin/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserByLogin(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByLogin(username));
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) throws Exception {
        return ResponseEntity.ok(userService.getUserInfo(username));
    }

    @PostMapping("/cname")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> cname(HttpServletRequest request, @RequestParam String username) {
        String header = request.getHeader("Authorization");
        return ResponseEntity.ok(userService.changeName(header, username));
    }

    @PostMapping("/description")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> cdesc(HttpServletRequest request, @RequestParam String description) {
        String header = request.getHeader("Authorization");
        return ResponseEntity.ok(userService.updateDescription(description, header));
    }

    @PostMapping("/avatar")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> cavatar(HttpServletRequest request, @RequestParam String avatarUrl) {
        String header = request.getHeader("Authorization");
        return ResponseEntity.ok(userService.updateAvatar(avatarUrl, header));
    }
}
