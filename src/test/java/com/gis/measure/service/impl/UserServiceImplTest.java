package com.gis.measure.service.impl;

import com.gis.measure.dataobject.User;
import com.gis.measure.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void findOne(){
        User user = userService.findOne("1701400028");
        Assert.assertNotNull(user);
    }

    @Test
    public void create(){
        User user = new User();
        user.setStudentId("1201400028");
        user.setPassword("123456");
        user.setStudentName("Greeny");
        user.setStudentGroup(9);
        user.setStudentClass(121);
        user.setVerificationCode("1998");
        user.setStudentGrade(12);

        User result = userService.create(user);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateGroup(){
        User result = userService.updateGroup("1701400028", 13);
        Assert.assertNotEquals(new Integer(0), result.getStudentGroup());
    }

    @Test
    public void updatePassword(){
        User result = userService.updatePassword("1701400028", "yuyuhui");
        Assert.assertEquals("yuyuhui", result.getPassword());
    }

    @Test
    public void delete(){
        userService.delete("1701400028");
    }
}