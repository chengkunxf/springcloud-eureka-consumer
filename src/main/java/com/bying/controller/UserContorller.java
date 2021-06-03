package com.bying.controller;

import com.bying.pojo.User;
import com.bying.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/5/28 2:45 下午
 * @description
 */
@RestController
public class UserContorller {

    @Resource
    public UserService userService;

    @RequestMapping(value = "/consumer", produces = {"application/json;charset=UTF-8"})
    public List<User> getUsers() {
        return userService.getUser();
    }
}
