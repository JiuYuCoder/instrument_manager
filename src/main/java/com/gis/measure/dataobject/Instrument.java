package com.gis.measure.dataobject;

import com.gis.measure.enums.InstrumentStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Instrument {

    /**仪器码*/
    @Id
    private String instrumentId;

    /**仪器名称*/
    private String instrumentName;

    /**仪器是否归还，默认为未归还*/
    private Integer isReturn = InstrumentStatusEnum.NOT_RETURN.getCode();
}
