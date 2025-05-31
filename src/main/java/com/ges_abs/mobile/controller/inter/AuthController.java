package com.ges_abs.mobile.controller.inter;

import com.ges_abs.mobile.dto.request.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/api/mobile/auth")
public interface AuthController {
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest);

}

