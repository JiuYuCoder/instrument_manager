package com.gis.measure.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ManagerForm {
    /**学号*/
    @NotNull
    private String studentId;

    /**学生姓名*/
    @NotNull
    private String studentName;

    /**学生年级，例如：17*/
    @NotNull
    private Integer studentGrade;

    /**学生班级，如：1*/
    @NotNull
    private Integer studentClass;
}
