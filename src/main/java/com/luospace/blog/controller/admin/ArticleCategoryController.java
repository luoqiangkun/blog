package com.luospace.blog.controller.admin;

import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.common.Result;
import com.luospace.blog.entity.Article;
import com.luospace.blog.entity.ArticleCategory;
import com.luospace.blog.service.ArticleCategoryService;
import com.luospace.blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin/article-category")
public class ArticleCategoryController {

    @Resource
    ArticleCategoryService articleCategoryService;

    @GetMapping("/index")
    public String index(){
        return "admin/article-category/index";
    }

    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String,Object> params){
        QueryParams queryParams = new QueryParams(params);
        Page<ArticleCategory> page = articleCategoryService.getList(queryParams);
        return Result.success(page);
    }

    @GetMapping("/create")
    public String create(){
        return "admin/article-category/create";
    }

    @ResponseBody
    @PostMapping("/create")
    public Result create(@RequestBody ArticleCategory articleCategory){
        int count = articleCategoryService.add(articleCategory);
        if(count > 0){
            return Result.success(articleCategory);
        }else {
            return Result.failed();
        }
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int articleCategoryId, Model model){
        ArticleCategory articleCategory = articleCategoryService.get(articleCategoryId);
        model.addAttribute("articleCategory",articleCategory);
        return "admin/article-category/update";
    }

    @ResponseBody
    @PostMapping("/update")
    public Result update(@RequestBody ArticleCategory articleCategory){
        int count = articleCategoryService.edit(articleCategory);
        if(count > 0){
            return Result.success(articleCategory);
        }else {
            return Result.failed();
        }
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") int articleCategoryId){
        int count = articleCategoryService.remove(articleCategoryId);
        if(count > 0){
            return Result.success(articleCategoryId);
        }else {
            return Result.failed();
        }
    }
}
