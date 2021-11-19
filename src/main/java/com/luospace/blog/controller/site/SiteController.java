package com.luospace.blog.controller.site;

import com.luospace.blog.common.Constants;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.common.Result;
import com.luospace.blog.entity.Article;
import com.luospace.blog.entity.ArticleCategory;
import com.luospace.blog.service.ArticleCategoryService;
import com.luospace.blog.service.ArticleService;
import com.luospace.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class SiteController {

    protected static Logger logger = LoggerFactory.getLogger(SiteController.class);

    @Resource
    UserService userService;

    @Resource
    ArticleService articleService;

    @Resource
    ArticleCategoryService articleCategoryService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpSession session, Model model){
        //获取分类列表
        List<ArticleCategory> articleCategories = articleCategoryService.list(new QueryParams());
        model.addAttribute("articleCategories",articleCategories);
        //获取文章列表
        List<Article> articles = articleService.list(new QueryParams());
        model.addAttribute("articles",articles);

        logger.info("Hello world");
        return "/site/index";
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        return "/site/login";
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


}
