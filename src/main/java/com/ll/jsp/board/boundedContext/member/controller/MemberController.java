package com.ll.jsp.board.boundedContext.member.controller;

import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.global.base.Rq;
import com.ll.jsp.board.boundedContext.member.service.MemberService;

public class MemberController {
    private final MemberService memberService;

    public MemberController () {
        memberService = Container.memberService;
    }

    public void showJoin(Rq rq) {

        rq.view("/usr/member/join");
    }
}
