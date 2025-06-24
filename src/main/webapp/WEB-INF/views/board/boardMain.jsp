<%--
  Created by IntelliJ IDEA.
  User: monae
  Date: 25. 6. 24.
  Time: 오후 6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 메인</title>
</head>
<body>
    <pre>${list}</pre>
</body>
</html>
<h2>게시글 목록</h2>
<c:forEach var="post" items="${list}">
    <div>
        <p>게시판 종류: ${post.boardType}</p>
        <p>제목: ${post.postTitle}</p>
        <p>작성자: ${post.postWriter}</p>
        <p>작성일: ${post.writeAt}</p>
        <p>조회수: ${post.viewCount}</p>
        <hr>
    </div>
</c:forEach>