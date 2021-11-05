package com.luospace.blog.controller.admin;

import com.luospace.blog.common.Result;
import com.luospace.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/password")
    public String password(){
        return "/admin/user/password";
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpSession session){
        if(!StringUtils.hasLength(password) || !StringUtils.hasLength(confirmPassword)){
            return Result.failed("密码不能为空");
        }
        if(!password.equals(confirmPassword)){
            return  Result.failed("两次密码输入不一致");
        }
        Integer userid = (Integer) session.getAttribute("userid");
        Result result = userService.changePassword(userid,password);
        return result;
    }
}
