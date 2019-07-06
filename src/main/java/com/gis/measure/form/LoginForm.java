package com.gis.measure.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    @NotNull(message = "学号不能为空")
    private String studentId;

    @NotNull(message = "密码不能为空")
    private String password;
}
