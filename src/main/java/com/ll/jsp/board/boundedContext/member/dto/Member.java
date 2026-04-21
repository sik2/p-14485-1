package com.ll.jsp.board.boundedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    private long id;
    private String username;
    private String password;
    private String name;
}
