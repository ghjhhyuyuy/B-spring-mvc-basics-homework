package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.InvalidParamsException;
import com.thoughtworks.capacity.gtb.mvc.result.Result;
import com.thoughtworks.capacity.gtb.mvc.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by wzw on 2020/9/10.
 */
@RestController
public class UserOperationApi {
    @Autowired
    private UserOperationService userOperationService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) throws InvalidParamsException {
        userOperationService.register(user);
        return ResponseEntity.created(null).build();
    }
}
