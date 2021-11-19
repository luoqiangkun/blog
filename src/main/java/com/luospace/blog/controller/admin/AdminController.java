package com.luospace.blog.controller.admin;

import com.luospace.blog.common.Constants;
import com.luospace.blog.common.Result;
import com.luospace.blog.entity.User;
import com.luospace.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    UserService userService;

    @GetMapping("/index")
    public String index(HttpSession session, Model model){
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        String userid = (String) session.getAttribute("userid");
        if( StringUtils.hasLength(userid) ){
            return "redirect:admin/index";
        }
        return "admin/login";
    }

    @GetMapping("/register")
    public String register(){
        return "admin/register";
    }

    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        @RequestParam("captcha") String captcha,
                        HttpSession session){
        if(!StringUtils.hasLength(name)){
            return Result.failed("用户名不能为空");
        }
        if(!StringUtils.hasLength(password)){
            return Result.failed("密码不能为空");
        }
        if(!StringUtils.hasLength(captcha)){
            return Result.failed("验证码不能为空");
        }
        String sessionCaptcha = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equals(sessionCaptcha)){
            return Result.failed("验证码不不正确");
        }
        Result result = userService.login(name,password,session);
        return result;
    }

    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("retypePassword") String retypePassword){
        if(!StringUtils.hasLength(name) || !StringUtils.hasLength(password) || !StringUtils.hasLength(retypePassword)){
            return Result.failed("用户名和密码不能为空");
        }
        if(!retypePassword.equals(password)){
            return  Result.failed("两次密码输入不一致");
        }
        Result result = userService.register(name,password);
        return result;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userid");
        return "/admin/login";
    }

}
