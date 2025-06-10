package com.ges_abs.services.inter;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.mobile.dto.request.LoginRequestDto;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(LoginRequestDto loginRequest);
}
