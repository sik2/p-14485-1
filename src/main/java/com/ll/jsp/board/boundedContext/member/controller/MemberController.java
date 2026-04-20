package com.ll.jsp.board.boundedContext.member.controller;

import com.ll.jsp.board.boundedContext.global.base.Rq;

public class MemberController {
    public void showJoin(Rq rq) {
        rq.appendBody("회원가입 화면");
    }
}
