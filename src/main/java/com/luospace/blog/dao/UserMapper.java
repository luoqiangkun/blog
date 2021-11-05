package com.luospace.blog.dao;

import com.luospace.blog.entity.User;

public interface UserMapper {

    Integer insert(User user);

    Integer update(User user);

    User getUserById(Integer id);

    User getUserByName(String name);

    User getUserByNameAndPassword(String name,String password);
}
