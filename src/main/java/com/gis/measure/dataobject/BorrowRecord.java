package com.gis.measure.dataobject;

import com.gis.measure.enums.ConfirmStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class BorrowRecord {

    /**记录Id，由工具生成，唯一*/
    @Id
    private String borrowId;

    /**学号*/
    private String studentId;

    /**姓名*/
    private String studentName;

    /**仪器码*/
    private String instrumentId;

    /**仪器名称*/
    private String instrumentName;

    /**管理员是否确认，默认为未确认*/
    private Integer isConfirm = ConfirmStatusEnum.NOT_CONFIRM.getCode();

    /** 申请时间 */
    private Date borrowTime;

    /** 确认时间 */
    private Date confirmTime;
}
