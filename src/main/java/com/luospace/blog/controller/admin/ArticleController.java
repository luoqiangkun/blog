package com.luospace.blog.controller.admin;

import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.common.Result;
import com.luospace.blog.entity.Article;
import com.luospace.blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @GetMapping("/index")
    public String index(){
        return "admin/article/index";
    }

    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String,Object> params){
        QueryParams queryParams = new QueryParams(params);
        Page<Article> page = articleService.getList(queryParams);
        return Result.success(page);
    }

    @GetMapping("/create")
    public String create(){
        return "admin/article/create";
    }

    @ResponseBody
    @PostMapping("/create")
    public Result create(@RequestBody Article article,HttpSession session){
        article.setUserId(Integer.parseInt(session.getAttribute("userid").toString()));
        int count = articleService.add(article);
        if(count > 0){
            return Result.success(article);
        }else {
            return Result.failed();
        }
    }

    @GetMapping("/update")
    public String update(@RequestParam("articleId") int articleId, Model model){
        Article article = articleService.get(articleId);
        model.addAttribute("article",article);
        return "admin/article/update";
    }

    @ResponseBody
    @PostMapping("/update")
    public Result update(@RequestBody Article article){
        int count = articleService.edit(article);
        if(count > 0){
            return Result.success(article);
        }else {
            return Result.failed();
        }
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(@RequestParam("articleId") int articleId){
        int count = articleService.remove(articleId);
        if(count > 0){
            return Result.success(articleId);
        }else {
            return Result.failed();
        }
    }
}
