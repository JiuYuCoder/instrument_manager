package com.gis.measure.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Manager {
    /**
     * 学号
     */
    @Id
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生年级
     */
    private Integer studentGrade;

    /**
     * 学生班级
     */
    private Integer studentClass;

}
