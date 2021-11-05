package com.luospace.blog.controller.site;

import com.luospace.blog.common.Result;
import com.luospace.blog.entity.Article;
import com.luospace.blog.service.ArticleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

public class ArticleController {

    @Resource
    ArticleService articleService;

    public String index(){
        return "/site/article/index";
    }

    @GetMapping("/article")
    public String detail(@RequestParam("id") int id, Model model){
        Article article = articleService.get(id);
        model.addAttribute("article",article);
        return "/site/article/detail";
    }

    public Result list(){
        return Result.success(null);
    }
}
