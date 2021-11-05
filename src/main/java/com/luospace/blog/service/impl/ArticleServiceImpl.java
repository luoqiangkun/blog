package com.luospace.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.luospace.blog.common.Page;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.dao.ArticleMapper;
import com.luospace.blog.entity.Article;
import com.luospace.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleMapper articleMapper;

    @Override
    public Article get(int articleId){
        Article article = articleMapper.selectByPrimaryKey(articleId);
        return article;
    }

    public List<Article> list(QueryParams queryParams){
        List<Article> articles = articleMapper.select(queryParams);
        return articles;
    }

    @Override
    public Page<Article> getList(QueryParams queryParams) {
        PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());
        List<Article> articles = articleMapper.select(queryParams);
        Page<Article> page = new Page<Article>(articles);
        return page;
    }

    @Override
    public int add(Article article){
        return articleMapper.insert(article);
    }

    @Override
    public int edit(Article article) {
        return articleMapper.updateByPrimaryKey(article);
    }

    @Override
    public int save(Article article) {
        int articleId = article.getArticleId();
        if(articleId > 0){
            return add(article);
        }else{
            return edit(article);
        }
    }

    @Override
    public int remove(int articleId) {
        return articleMapper.deleteByPrimaryKey(articleId);
    }
}
