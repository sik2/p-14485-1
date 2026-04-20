package com.ll.jsp.board.boundedContext.article.controller;

import com.ll.jsp.board.boundedContext.article.dto.Article;
import com.ll.jsp.board.boundedContext.article.service.ArticleService;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;

import java.util.List;

public class ArticleController {

    private ArticleService articleService;

    public ArticleController() {
        articleService = Container.articleService;
    }

    public void showList(Rq rq) {
        List<Article> articleList = articleService.findAll();

        rq.setAttr("articleList", articleList);
        rq.view("/usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("/usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        if (title.isBlank()) {
            rq.appendBody("""
                    <script>
                        alert('제목을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }
        String content = rq.getParam("content", "");
        if (content.isBlank()) {
            rq.appendBody("""
                    <script>
                        alert('제목을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }

        Article article = articleService.create(title, content);

        rq.appendBody("""
                <div>%d 게시물 생성</div>
                <div>제목 : %s</div>
                <div>내용 : %s</div>
                <a href="/usr/article/list">목록으로</a>
                """.formatted(article.getId(), title, content));
    }

    public void detail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id <= 0) {
            rq.appendBody("""
                    <script>
                        alert('잘못된 접근입니다.');
                        history.back();
                    </script>
                    """);
            return;
        }

        Article article = articleService.findById(id);
        if (article == null) {
            rq.appendBody("""
                    <script>
                        alert('게시글을 찾을 수 없습니다.');
                        history.back();
                    </script>
                    """);
            return;
        }

        rq.setAttr("article", article);
        rq.view("/usr/article/detail");
    }
}
