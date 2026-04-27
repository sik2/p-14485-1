package com.ll.jsp.board.boundedContext.global;

import com.ll.jsp.board.boundedContext.article.controller.ArticleController;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;
import com.ll.jsp.board.boundedContext.member.controller.MemberController;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
    private MemberController memberController;
    private ArticleController articleController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.memberController = Container.memberController;
        this.articleController = Container.articleController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        switch (rq.getMethod()) {
            case "GET" -> {
                switch (rq.getActionPath()) {
                    case "/usr/article/list" -> articleController.showList(rq);
                    case "/usr/article/write" -> articleController.showWrite(rq);
                    case "/usr/article/detail" -> articleController.detail(rq);
                    case "/usr/article/modify" -> articleController.showModify(rq);
                    case "/usr/member/join" -> memberController.showJoin(rq);
                    case "/usr/member/login" -> memberController.showLogin(rq);
                    case "/usr/member/me" -> memberController.showMe(rq);
                }
            }
            case "POST" -> {
                // /usr/article/modify
                switch (rq.getActionPath()) {
                    case "/usr/article/write" -> articleController.doWrite(rq);
                    case "/usr/article/modify" -> articleController.doModify(rq);
                    case "/usr/article/delete" -> articleController.doDelete(rq);
                    case "/usr/member/join" -> memberController.doJoin(rq);
                    case "/usr/member/login" -> memberController.doLogin(rq);
                    case "/usr/member/logout" -> memberController.doLogout(rq);
                }
            }
        }
    }
}
