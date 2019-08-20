package com.test.shiro.dao;

import com.test.shiro.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void selectByAccount() {
//        User user = userMapper.selectByAccount("tom");
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
    }
}