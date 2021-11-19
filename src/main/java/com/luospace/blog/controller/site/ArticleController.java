package com.luospace.blog.controller.site;

import com.luospace.blog.entity.Article;
import com.luospace.blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ArticleController {
    @Resource
    ArticleService articleService;
    @ResponseBody
    @GetMapping("/test")
    public String index(){
        return "index";
    }

    @RequestMapping("/article/{articleId}")
    public String detail(@PathVariable("articleId") Integer articleId, Model model){
        //获取文章详情
        Article article = articleService.get(articleId);
        model.addAttribute("article",article);
        return "/site/article/detail";
    }
}
