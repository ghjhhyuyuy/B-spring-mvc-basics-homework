package com.thoughtworks.capacity.gtb.mvc.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wzw on 2020/9/10.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserOperationApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        String userJson = "{\"id\":1,\"userName\":\"wangzuowen\",\"password\":\"123456\",\"email\":\"798@qq.com\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andReturn();
    }
    @Test
    public void should_add_user_when_given_correct_parameters() throws Exception {
        String userJson = "{\"userName\":\"tomtom\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    public void should_throw_exception_when_username_is_null() throws Exception {
        String userJson = "{\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不能为空")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_password_is_null() throws Exception {
        String userJson = "{\"userName\":\"tomtom\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不能为空")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_username_is_invalid() throws Exception {
        String userJson = "{\"userName\":\"to\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_password_is_invalid() throws Exception {
        String userJson = "{\"userName\":\"tomtom\",\"password\":\"12\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_email_is_invalid() throws Exception {
        String userJson = "{\"userName\":\"tomtom\",\"password\":\"123456\",\"email\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("邮箱地址不合法")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_user_is_exist() throws Exception {
        String userJson = "{\"userName\":\"wangzuowen\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(jsonPath("$.message", is("用户已存在")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_return_user_message_when_username_and_password_is_right() throws Exception {
        String userJson = "{\"userName\":\"wangzuowen\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    public void should_throw_exception_when_username_and_password_is_wrong() throws Exception {
        String userJson = "{\"userName\":\"wangzuowen\",\"password\":\"1234567\"}";
        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名或密码错误")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_login_password_is_invalid() throws Exception {
        String userJson = "{\"userName\":\"wangzuowen\",\"password\":\"12\"}";
        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }

    @Test
    public void should_throw_exception_when_login_username_is_invalid() throws Exception {
        String userJson = "{\"userName\":\"wa\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")))
                .andExpect(jsonPath("$.code", is(400)))
                .andReturn();
    }
}