package com.luospace.blog.service;

import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {
    public ArticleCategory get(int categoryId);

    public List<ArticleCategory> list(QueryParams queryParams);

    public Page<ArticleCategory> getList(QueryParams queryParams);

    public int add(ArticleCategory articleCategory);

    public int edit(ArticleCategory articleCategory);

    public int save(ArticleCategory articleCategory);

    public int remove(int categoryId);

}
