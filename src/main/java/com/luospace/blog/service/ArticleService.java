package com.luospace.blog.service;

import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.Article;

import java.util.List;

public interface ArticleService {
    public Article get(int articleId);

    public List<Article> list(QueryParams queryParams);

    public Page<Article> getList(QueryParams queryParams);

    public int add(Article article);

    public int edit(Article article);

    public int save(Article article);

    public int remove(int articleId);

}
