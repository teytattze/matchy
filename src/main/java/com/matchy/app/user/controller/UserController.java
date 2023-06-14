package com.matchy.app.user.controller;

import com.matchy.app.user.controller.dto.*;
import com.matchy.app.user.domain.UserService;
import com.matchy.app.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> userResponses = userService
            .getUsers()
            .stream()
            .map(userMapper::toResponse)
            .toList();
        return ResponseEntity.ok(userResponses);
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(
        @RequestBody CreateUserRequestBody body
    ) {
        UUID newUserId = userService.createUser(
            body.email(),
            body.password(),
            body.firstName(),
            body.lastName(),
            body.roles()
        );
        return ResponseEntity.ok(newUserId);
    }

    @PostMapping("/emails")
    public ResponseEntity<UserResponse> getUserByEmail(
        @RequestBody GetUserByEmailRequestBody body
    ) {
        var user = userService.getUserByEmail(body.email());
        var userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<UUID> signup(
        @RequestBody SignUpRequestBody body
    ) {
        UUID newUserId = userService.createUser(
            body.email(),
            body.password(),
            body.firstName(),
            body.lastName(),
            body.roles()
        );
        return ResponseEntity.ok(newUserId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UUID> updateUserById(
        @PathVariable UUID id,
        @RequestBody UpdateUserRequestBody body
    ) {
        var userId = userService.updateUserById(
            id,
            body.firstName(),
            body.lastName(),
            null
        );
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
        @PathVariable UUID id
    ) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
