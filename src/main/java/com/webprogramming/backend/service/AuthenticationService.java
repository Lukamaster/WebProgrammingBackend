package com.webprogramming.backend.service;

import com.webprogramming.backend.model.dto.AuthenticationRequest;
import com.webprogramming.backend.model.dto.AuthenticationResponse;
import com.webprogramming.backend.model.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
