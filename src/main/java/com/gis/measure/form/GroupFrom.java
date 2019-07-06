package com.gis.measure.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GroupFrom {
    @NotNull
    private String studentId;

    @NotNull
    private Integer studentGroup;
}
