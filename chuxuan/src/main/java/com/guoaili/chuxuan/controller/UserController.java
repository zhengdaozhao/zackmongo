package com.guoaili.chuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.chuxuan.DTO.UserVo;
import com.guoaili.chuxuan.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public ResponseEntity<UserVo> getCurrentUser() {
        return ResponseEntity.ok().body(new UserVo(userService.getUser().getUsername(),null));
    }

}
