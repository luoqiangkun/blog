package com.luospace.blog.dao;

import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryMapper {
    List<ArticleCategory> select(QueryParams queryParams);

    int insert(ArticleCategory articleCategory);

    ArticleCategory selectByPrimaryKey(int categoryId);

    int updateByPrimaryKey(ArticleCategory articleCategory);

    int deleteByPrimaryKey(int categoryId);
}
