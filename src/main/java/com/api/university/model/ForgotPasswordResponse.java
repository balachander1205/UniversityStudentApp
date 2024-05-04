package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordResponse {
    private String message;
    private boolean isUserExists;
    private String resetLink;
}
