package com.springbootblog.service;

import com.springbootblog.po.User;
import org.springframework.stereotype.Service;

public interface UserService {
    /**
     * 检查用户登录
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);
}
