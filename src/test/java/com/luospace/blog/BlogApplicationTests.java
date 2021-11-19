package com.luospace.blog;

import com.github.pagehelper.PageInfo;
import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.Article;
import com.luospace.blog.entity.ArticleCategory;
import com.luospace.blog.service.ArticleCategoryService;
import com.luospace.blog.service.ArticleService;
import com.luospace.blog.service.UserService;
import com.luospace.blog.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    UserService userService;

    @Resource
    ArticleService articleService;

    @Resource
    ArticleCategoryService articleCategoryService;

    @Resource
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        System.out.print("111111");
    }

    @Test
    void test01(){
        System.out.print(userService.getUserById(1));
    }

    @Test
    void test02(){
        QueryParams queryParams = new QueryParams();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pageNum",1);
        params.put("pageSize",10);
        params.put("articleTitle","测试");
        Page<Article> page = articleService.getList(queryParams);
        System.out.print(page);
    }

    @Test
    void test03(){
        int articleId = 1;
        Article article = articleService.get(articleId);
        System.out.print(article);
    }

    @Test
    void  test04(){
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("国际");
        articleCategory.setCategorySort(1);
        articleCategory.setCategoryEnabled(1);
        int count = articleCategoryService.add(articleCategory);
        System.out.print(count);
    }

    @Test
    void test05(){
        redisUtil.set("test","hello world");
        System.out.print(redisUtil.get("test"));
    }
}
