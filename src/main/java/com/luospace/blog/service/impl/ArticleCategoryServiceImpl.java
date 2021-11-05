package com.luospace.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.dao.ArticleCategoryMapper;
import com.luospace.blog.dao.ArticleMapper;
import com.luospace.blog.entity.Article;
import com.luospace.blog.entity.ArticleCategory;
import com.luospace.blog.service.ArticleCategoryService;
import com.luospace.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    public ArticleCategory get(int categoryId){
        ArticleCategory articleCategory = articleCategoryMapper.selectByPrimaryKey(categoryId);
        return articleCategory;
    }

    @Override
    public List<ArticleCategory> list(QueryParams queryParams){
        List<ArticleCategory> articleCategory = articleCategoryMapper.select(queryParams);
        return articleCategory;
    }

    @Override
    public Page<ArticleCategory> getList(QueryParams queryParams) {
        PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());
        List<ArticleCategory> articleCategory = articleCategoryMapper.select(queryParams);
        Page<ArticleCategory> page = new Page<ArticleCategory>(articleCategory);
        return page;
    }

    @Override
    public int add(ArticleCategory articleCategory){
        return articleCategoryMapper.insert(articleCategory);
    }

    @Override
    public int edit(ArticleCategory articleCategory) {
        return articleCategoryMapper.updateByPrimaryKey(articleCategory);
    }

    @Override
    public int save(ArticleCategory articleCategory) {
        int categoryId = articleCategory.getCategoryId();
        if(categoryId > 0){
            return add(articleCategory);
        }else{
            return edit(articleCategory);
        }
    }

    @Override
    public int remove(int categoryId) {
        return articleCategoryMapper.deleteByPrimaryKey(categoryId);
    }
}
