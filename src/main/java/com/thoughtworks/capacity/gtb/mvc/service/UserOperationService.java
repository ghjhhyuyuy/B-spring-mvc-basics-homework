package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzw on 2020/9/10.
 */
@Service
public class UserOperationService {
    private List<User> userList = new ArrayList<>();
    public UserOperationService(List<User> userList){
        this.userList = userList;
    }

    public void register(User user) {
        userList.add(user);
    }
}
