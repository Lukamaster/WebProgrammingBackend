package com.webprogramming.backend.service.impl;

import com.webprogramming.backend.model.dto.AuthenticationRequest;
import com.webprogramming.backend.model.dto.AuthenticationResponse;
import com.webprogramming.backend.model.dto.RegisterRequest;
import com.webprogramming.backend.service.AuthenticationService;
import com.webprogramming.backend.service.UserService;
import com.webprogramming.backend.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        AppUser appUser = userService.saveUser(request);
        String jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        AppUser appUser = userService.findByEmail(request.getEmail());

        String jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}