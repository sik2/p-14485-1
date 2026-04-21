package com.ll.jsp.board.boundedContext.member.service;

import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.member.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService() {
        memberRepository = Container.memberRepository;
    }
}
