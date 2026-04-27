package com.ll.jsp.board.boundedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    private long id;
    private String username;
    private String password;
    private String name;
    private LocalDateTime regDate;

    public Member(Map<String, Object> row) {
        this.id = ((BigInteger) row.get("id")).longValue();
        this.username = (String) row.get("username");
        this.password = (String) row.get("password");
        this.name = (String) row.get("name");
        this.regDate = (LocalDateTime) row.get("regDate");
    }
}
