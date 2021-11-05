package com.luospace.blog.controller.site;

import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.ArticleCategory;
import com.luospace.blog.entity.User;
import com.luospace.blog.service.ArticleCategoryService;
import com.luospace.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SiteController {

    @Resource
    UserService userService;

    @Resource
    ArticleCategoryService articleCategoryService;

    @GetMapping("/index")
    public String index(HttpSession session, Model model){
        //用户数据
        int userid = (int)session.getAttribute("userid");
        if(userid > 0){
            model.addAttribute("isLogin",true);
            User user = userService.getUserById(userid);
            model.addAttribute("user",user);
        }else{
            model.addAttribute("isLogin",false);
        }
        //获取分类列表
        List<ArticleCategory> articleCategories = articleCategoryService.list(new QueryParams());
        model.addAttribute("articleCategories",articleCategories);
        return "/site/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/site/login";
    }
}
