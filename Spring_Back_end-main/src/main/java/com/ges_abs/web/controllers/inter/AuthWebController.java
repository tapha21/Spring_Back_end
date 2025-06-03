package com.ges_abs.web.controllers.inter;

import com.ges_abs.mobile.dto.request.LoginRequestDto;
import com.ges_abs.web.dto.request.LoginWebRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/web/auth")
public interface AuthWebController {
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginWebRequestDto loginRequest);

}
