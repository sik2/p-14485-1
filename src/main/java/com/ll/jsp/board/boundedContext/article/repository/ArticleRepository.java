package com.ll.jsp.board.boundedContext.article.repository;

import com.ll.jsp.board.boundedContext.article.dto.ArticleDto;
import com.ll.jsp.board.boundedContext.article.entity.Article;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    private final DBConnection dbConnection;

    public ArticleRepository() {
        dbConnection = Container.dbConnection;
    }

    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows("select * from article");

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);

            articleList.add(article);
        }

        return articleList;
    }

    public List<ArticleDto> joinMemberFindAll() {
        List<ArticleDto> articleDtoList = new ArrayList<>();

        List<Map<String, Object>> rows = dbConnection.selectRows("""
                SELECT
                A.id,
                A.title,
                A.content,
                M.username,
                A.regDate
                FROM `article` AS A
                INNER JOIN `member` as M
                on A.member_id = M.id
                """);

        for (Map<String, Object> row : rows) {
            ArticleDto articleDto = new ArticleDto(row);

            articleDtoList.add(articleDto);
        }

        return articleDtoList;
    }

    public long save(String title, String content) {
        int id = dbConnection.insert(
                        """
                            INSERT INTO article
                            SET title = '%s',
                            content='%s',
                            regDate=now()
                        """.formatted(title, content)
        );

        return id;
    }

    public ArticleDto joinMemberFindById(long id) {
        Map<String, Object> row = dbConnection.selectRow("""
            SELECT 
            A.id,
            A.title,
            A.content,
            A.regDate,
            M.username
            FROM article AS A
            INNER JOIN `member` AS M
            ON A.member_id = M.id
            WHERE A.id = %d
        """.formatted(id));

        return new ArticleDto(row);
    }

    public Article findById(long id) {
        Map<String, Object> row = dbConnection.selectRow("select * from article where id = %d".formatted(id));

        return new Article(row);
    }

    public void modify(long id, String title, String content) {
        dbConnection.update("""
                UPDATE article
                SET title = '%s',
                content = '%s'
                WHERE id = %d
                """.formatted(title, content, id));
    }

    public void delete(Article article) {
        dbConnection.delete("delete from article where id = %d".formatted(article.getId()));
    }
}
