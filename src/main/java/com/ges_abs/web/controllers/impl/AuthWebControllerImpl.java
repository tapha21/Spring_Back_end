package com.ges_abs.web.controllers.impl;

import com.ges_abs.security.JWTUtil;
import com.ges_abs.security.MyUserDetailsService;
import com.ges_abs.web.controllers.inter.AuthWebController;
import com.ges_abs.web.dto.request.LoginWebRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthWebControllerImpl implements AuthWebController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;

        @Override
        public ResponseEntity<?> login(LoginWebRequestDto loginRequest) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());
            final String jwt = jwtUtil.generateToken(loginRequest.getLogin());

            return ResponseEntity.ok(jwt);
        }
}