package com.guoaili.chuxuan.service;

import com.guoaili.chuxuan.DTO.UserVo;
import com.guoaili.chuxuan.entity.User;

public interface UserService {

    User findByName(String username);

    User getUser();

    void saveUser(UserVo uv);

    UserVo login(UserVo uv);
    
}
