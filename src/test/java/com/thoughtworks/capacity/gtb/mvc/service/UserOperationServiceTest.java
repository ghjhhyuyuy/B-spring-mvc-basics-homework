package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.InvalidParamsException;
import org.junit.Before;
import org.junit.Test;

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
        User user = new User(1,"wangzuowen","798@qq.com","123123");
        userList.add(user);
    }
    @Test
    public void should_add_user_when_given_correct_parameters() throws InvalidParamsException {
        UserOperationService userOperationService = new UserOperationService(userList);
        User user = new User(null,"zuowen",null,"111111");
        userOperationService.register(user);
        assertEquals(2,userList.size());
        assertEquals("zuowen",userList.get(1).getUserName());
        assertEquals("111111",userList.get(1).getPassword());
    }


    @Test
    public void should_throw_exception_when_user_is_exist(){
        UserOperationService userOperationService = new UserOperationService(userList);
        User user = new User(null,"wangzuowen",null,"123123");
        Throwable exception = assertThrows(InvalidParamsException.class,
                () -> userOperationService.register(user));
        assertEquals("用户已存在",exception.getMessage());
    }
}