package com.luospace.blog.service.impl;

import com.luospace.blog.common.Result;
import com.luospace.blog.dao.UserMapper;
import com.luospace.blog.entity.User;
import com.luospace.blog.service.UserService;
import com.luospace.blog.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }

    @Override
    public Result login(String name, String password, HttpSession session){
        User user = userMapper.getUserByNameAndPassword(name, MD5Util.getMD5(password));
        if(user != null){
            session.setAttribute("userid",user.getId());
            return Result.success(user);
        }else{
            return Result.failed("用户名或密码错误");
        }
    }

    public Result register(String name,String password){
        if(userMapper.getUserByName(name) != null){
            return Result.failed("用户名已存在");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(MD5Util.getMD5(password));
        Integer rows = userMapper.insert(user);
        if(rows > 0){
            return Result.success(user);
        }else{
            return Result.failed("用户注册失败");
        }
    }

    public Result changePassword(Integer userid,String password){
        User user = userMapper.getUserById(userid);
        if(user == null){
            return Result.failed("用户不存在");
        }
        if(password.equals(user.getPassword())){
            return Result.failed("新密码不能与原密码相同");
        }
        user.setPassword(MD5Util.getMD5(password));
        Integer rows = userMapper.update(user);
        if(rows > 0){
            return Result.success(user);
        }else{
            return Result.failed("用户注册失败");
        }
    }
}
