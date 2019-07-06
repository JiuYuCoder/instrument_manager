package com.gis.measure.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PasswordForm {
    @NotNull
    private String studentId;

    @NotNull
    private String password;
}
