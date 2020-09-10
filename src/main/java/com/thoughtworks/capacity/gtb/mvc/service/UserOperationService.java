package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.InvalidParamsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wzw on 2020/9/10.
 */
@Service
public class UserOperationService {
    private List<User> userList = new ArrayList<>();
    public UserOperationService(List<User> userList){
        this.userList = userList;
    }

    public void register(User user) throws InvalidParamsException {
        Stream<User> result = userList.stream().filter(theUser -> theUser.getUsername().equals(user.getUsername()));
        if(!(result.toArray().length == 0)){
            throw new InvalidParamsException("用户已存在");
        }
        userList.add(user);
    }

    public User login(User user) throws InvalidParamsException {
        List<User> result = userList.stream().filter(theUser -> theUser.getUsername().equals(user.getUsername()) && theUser.getPassword().equals(user.getPassword())).collect(Collectors.toList());
        if(result.isEmpty()){
            throw new InvalidParamsException("用户名或密码错误");
        }
        return result.get(0);
    }
}
