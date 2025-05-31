package com.ges_abs.web.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginWebRequestDto {
    private String login;
    private String password;
}