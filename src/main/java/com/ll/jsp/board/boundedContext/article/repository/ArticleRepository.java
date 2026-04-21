package com.ll.jsp.board.boundedContext.article.repository;

import com.ll.jsp.board.boundedContext.article.dto.Article;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private List<Article> articleList;
    private long lastId;

    public ArticleRepository() {
        articleList = new ArrayList<>();
        makeTestData();
        lastId = articleList.get(articleList.size() - 1).getId();
    }

    private void makeTestData() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Article article = new Article(i, "제목 " + i, "내용 " + i);
            articleList.add(article);
        });
    }

    public List<Article> findAll() {
        return articleList.stream()
                .sorted((Comparator.comparing(Article::getId).reversed()))
                .toList();
    }

    public Article save(String title, String content) {
        Article article = new Article(++lastId, title, content);
        articleList.add(article);

        return article;
    }

    public Article findById(long id) {
        return articleList.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void modify(long id, String title, String content) {
        Article article = findById(id);

        if (article == null) return;

        article.setTitle(title);
        article.setContent(content);
    }

    public void delete(Article article) {
        articleList.remove(article);
//        articleList.removeIf(a -> a.getId() == id);
    }
}
