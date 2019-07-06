package com.gis.measure.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserForm {
    /**学号*/
    @NotNull
    private String studentId;

    /**学生姓名*/
    @NotNull
    private String studentName;

    /**学生年级，例如：17*/
    @NotNull
    private Integer studentGrade;

    /**学生班级，如：181*/
    @NotNull
    private Integer studentClass;

    /**学生组号，如：9*/
    @NotNull
    private Integer studentGroup;

    /**登录密码*/
    @NotNull
    private String password;

    /**修改码，修改密码时使用*/
    @NotNull
    private String verificationCode;

    @Override
    public String toString() {
        return "UserForm{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentGrade=" + studentGrade +
                ", studentClass=" + studentClass +
                ", studentGroup=" + studentGroup +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
