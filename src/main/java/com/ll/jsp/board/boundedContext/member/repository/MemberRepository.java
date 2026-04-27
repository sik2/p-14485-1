package com.ll.jsp.board.boundedContext.member.repository;

import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.member.dto.Member;
import com.ll.jsp.board.db.DBConnection;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private List<Member> memberList;
    DBConnection dbConnection;

    public MemberRepository() {
        memberList = new ArrayList<>();
        dbConnection = Container.dbConnection;
    }

    public void save(String username, String password, String name) {
        String encryptedPassword = Base64.getEncoder().encodeToString((password + "salt"). getBytes());

        dbConnection.insert("""
                INSERT INTO `member`
                SET username = '%s',
                password = '%s',
                name = '%s',
                regDate = now()
                """.formatted(username, encryptedPassword, name));

    }

    public Member findByUsername(String username) {
        Map<String, Object> row = dbConnection.selectRow("""
            SELECT * 
            FROM `member` 
            WHERE username = '%s'
        """.formatted(username));

        if (row.isEmpty()) {
            return null;
        }

        return new Member(row);
    }
}
