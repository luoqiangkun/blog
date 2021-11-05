package com.luospace.blog.dao;
import com.luospace.blog.common.QueryParams;
import com.luospace.blog.entity.Article;
import java.util.List;

public interface ArticleMapper {
    List<Article> select(QueryParams queryParams);

    int insert(Article article);

    Article selectByPrimaryKey(int articleId);

    int updateByPrimaryKey(Article article);

    int deleteByPrimaryKey(int articleId);
}
