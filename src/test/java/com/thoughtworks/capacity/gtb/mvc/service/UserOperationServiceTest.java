package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Created by wzw on 2020/9/10.
 */
public class UserOperationServiceTest {
    private final List<User> userList = new ArrayList<>();
    @Before
    public void setUp() {
        User user = new User(1,"作文","798@qq.com","123");
        userList.add(user);
    }
    @Test
    public void should_add_user_when_given_correct_parameters(){
        UserOperationService userOperationService = new UserOperationService(userList);
        User user = new User(null,"zuowen",null,"111");
        userOperationService.register(user);
        assertEquals(2,userList.size());
        assertEquals("zuowen",userList.get(1).getUserName());
        assertEquals("111",userList.get(1).getPassword());
    }

    @Test
    public void should_throw_exception_when_username_is_null(){
        UserOperationService userOperationService = new UserOperationService(userList);
        User user = new User(null,null,null,"111");
        Throwable exception = assertThrows(Exception.class,
                () -> userOperationService.register(user));
        assertEquals("用户名不能为空",exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_password_is_null(){
        UserOperationService userOperationService = new UserOperationService(userList);
        User user = new User(null,"zuowen",null,null);
        Throwable exception = assertThrows(Exception.class,
                () -> userOperationService.register(user));
        assertEquals("密码不能为空",exception.getMessage());
    }
}