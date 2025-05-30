package com.ges_abs.web.controllers.inter;

import com.ges_abs.mobile.dto.request.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthWebController {
    @GetMapping("/login")
    ResponseEntity<?> login(LoginRequestDto loginRequest);

}
