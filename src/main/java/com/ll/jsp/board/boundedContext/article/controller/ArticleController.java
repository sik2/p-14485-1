package com.ll.jsp.board.boundedContext.article.controller;

import com.ll.jsp.board.boundedContext.article.dto.ArticleDto;
import com.ll.jsp.board.boundedContext.article.entity.Article;
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
        List<ArticleDto> articleDtoList = articleService.joinMemberFindAll();

        rq.setAttr("articleDtoList", articleDtoList);
        rq.view("/usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("/usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        if (title.isBlank()) {
            rq.replace("제목을 입력해주세요.", "/usr/article/wrtie");
            return;
        }
        String content = rq.getParam("content", "");
        if (content.isBlank()) {
            rq.replace("내용을 입력해주세요.", "/usr/article/write");
            return;
        }

        long id = articleService.create(title, content);

        rq.replace("%d 게시물이 작성되었습니다.".formatted(id), "/usr/article/detail/%d".formatted(id));
    }

    public void detail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id <= 0) {
            rq.historyBack("잘못된 접근입니다.");
            return;
        }

        Article article = articleService.findById(id);
        if (article == null) {
            rq.replace("게시글을 찾을 수 없습니다.", "/usr/article/list");
            return;
        }

        rq.setAttr("article", article);
        rq.view("/usr/article/detail");
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id <= 0) {
            rq.historyBack("잘못된 접근입니다.");
            return;
        }

       Article article = articleService.findById(id);
        if (article == null) {
            rq.replace("게시글을 찾을 수 없습니다.", "/usr/article/list");
            return;
        }

        rq.setAttr("article", article);
        rq.view("/usr/article/modify");
    }

    public void doModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        String title = rq.getParam("title", "");
        if (title.isBlank()) {
            rq.replace("제목을 입력해주세요.", "/usr/article/modify/%d".formatted(id));
            return;
        }

        String content = rq.getParam("content", "");
        if (content.isBlank()) {
            rq.replace("내용을 입력해주세요.", "/usr/article/modify/%d".formatted(id));
            return;
        }

        articleService.modify(id, title, content);
        rq.replace("%d번 게시물이 수정되었습니다.".formatted(id), "/usr/article/detail/%d".formatted(id));
    }

    public void doDelete(Rq rq) {
        long id = rq.getIntParam("deleteId", 0);

        if (id <= 0) {
            rq.historyBack("잘못된 접근입니다.");
            return;
        }

       Article article = articleService.findById(id);
        if (article == null) {
            rq.replace("게시글을 찾을 수 없습니다.", "/usr/article/list");
            return;
        }
        articleService.delete(article);

        rq.replace("%d번 게시물이 삭제 되었습니다.".formatted(id), "/usr/article/list");
    }
}
