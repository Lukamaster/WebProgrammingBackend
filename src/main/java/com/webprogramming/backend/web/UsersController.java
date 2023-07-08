package com.webprogramming.backend.web;

import com.webprogramming.backend.model.identity.AppUser;
import com.webprogramming.backend.model.dto.AppUserDetailsDto;
import com.webprogramming.backend.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "securityScheme")
public class UsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllRegisteredUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDetailsDto> getUserWithId(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
