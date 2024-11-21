package com.joaodinizaraujo.secretsantapicker.api.controller;

import com.joaodinizaraujo.secretsantapicker.api.model.User;
import com.joaodinizaraujo.secretsantapicker.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Retrieve a user by email",
            description = "Fetches a user from the database based on the provided email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/get-by-email/{email}")
    private ResponseEntity<User> getByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getByEmail(email));
    }

    @Operation(summary = "Insert a new user",
            description = "Creates and saves a new user in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/insert")
    private ResponseEntity<User> insert(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insert(user));
    }
}