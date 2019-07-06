package com.gis.measure.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InstrumentForm {
    @NotNull
    private String instrumentId;

    @NotNull
    private String instrumentName;
}
