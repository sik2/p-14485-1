package com.ll.jsp.board.boundedContext.article.service;

import com.ll.jsp.board.boundedContext.article.dto.ArticleDto;
import com.ll.jsp.board.boundedContext.article.entity.Article;
import com.ll.jsp.board.boundedContext.article.repository.ArticleRepository;
import com.ll.jsp.board.boundedContext.base.Container;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = Container.articleRepository;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<ArticleDto> joinMemberFindAll() {
        return articleRepository.joinMemberFindAll();
    }

    public long create(String title, String content) {
        return articleRepository.save(title, content);
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }

    public void modify(long id, String title, String content) {
        articleRepository.modify(id, title, content);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
