package com.luospace.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/config")
public class ConfigController {
    @GetMapping("/index")
    public String index(){
        return "admin/config/index";
    }

    @GetMapping("/article")
    public String article(){
        return "admin/config/article";
    }
}
