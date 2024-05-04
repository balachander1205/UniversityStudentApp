package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPassword {
    private String email;
    private String newPassword;
    private String successMessage;
    private String bindingResult;
}
