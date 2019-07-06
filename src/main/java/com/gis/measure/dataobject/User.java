package com.gis.measure.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    /**学号*/
    @Id
    private String studentId;

    /**学生姓名*/
    private String studentName;

    /**学生年级，例如：17*/
    private Integer studentGrade;

    /**学生班级，如：181*/
    private Integer studentClass;

    /**学生组号，如：9*/
    private Integer studentGroup;

    /**登录密码*/
    private String password;

    /**修改码，修改密码时使用*/
    private String verificationCode;

    @Override
    public String toString() {
        return "User{" +
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
