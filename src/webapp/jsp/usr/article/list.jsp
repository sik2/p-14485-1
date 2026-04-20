<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>게시물 리스트</h1>

<div>
    <table border="1">
        <thead>
        <tr>
            <th>번호</th>
            <th>내용</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articleList}" varStatus="status">
            <tr>
                <td>${article.id}</td>
                <td>${article.title}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/usr/article/write">글쓰기</a>
</div>