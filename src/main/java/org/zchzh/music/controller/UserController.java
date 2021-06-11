package org.zchzh.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.music.model.dto.UserDTO;
import org.zchzh.music.model.request.LoginReq;
import org.zchzh.music.model.request.RegisterReq;
import org.zchzh.music.service.UserService;

import javax.validation.Valid;

/**
 * @author zengchzh
 * @date 2021/6/11
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public UserDTO login(@Valid LoginReq req) {
        return userService.login(req);
    }

    @PostMapping("/register")
    public UserDTO register(@Valid RegisterReq req) {
        return userService.register(req);
    }
}
