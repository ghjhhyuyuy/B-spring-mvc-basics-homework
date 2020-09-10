package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.InvalidParamsException;
import com.thoughtworks.capacity.gtb.mvc.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @GetMapping("/login")
    public ResponseEntity login(@NotNull(message = "用户名不能为空")
                                @Size(min = 3, max = 10, message = "用户名不合法")
                                @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$", message = "用户名不合法") String username, @NotNull(message = "密码不能为空")
                                @Size(min = 5, max = 12, message = "密码不合法") String password) throws InvalidParamsException {
        User user = new User(null, username, null, password);
        userOperationService.login(user);
        return ResponseEntity.ok().build();
    }
}
