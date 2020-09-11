package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * Created by wzw on 2020/9/10.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3,max = 10,message = "用户名不合法")
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$",message = "用户名不合法")
    private String username;
    @Email(message = "邮箱地址不合法")
    private String email;
    @NotBlank(message = "密码不能为空")
    @Size(min = 5,max = 12,message = "密码不合法")
    private String password;
}
