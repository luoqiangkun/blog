package com.luospace.blog.service;

import com.luospace.blog.common.Result;
import com.luospace.blog.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    User getUserById(Integer id);

    Result login(String name, String password, HttpSession session);

    Result register(String name,String password);

    Result changePassword(Integer userid,String password);
}
