package com.luospace.blog.vo;

import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.ArticleCategory;
import com.luospace.blog.service.ArticleCategoryService;

import javax.annotation.Resource;
import java.util.List;

public class SiteLayoutVo {
    List<ArticleCategory> articleCategories;

    @Resource
    ArticleCategoryService articleCategoryService;
    public SiteLayoutVo() {
        this.articleCategories = articleCategoryService.list(new QueryParams());
    }

    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }
}
