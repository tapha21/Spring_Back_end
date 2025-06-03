package com.ges_abs.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthWebResponseDto {
    private String token;
    private UserWithoutPasswordDto user;
}
