
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>board</title>
</head>
<body>
<h1>
    내가 만든 쿠키이이이이이222EEEEE
</h1>
<div>
    <c:forEach var="resultRow" items="${resultRow}">
        <p style="color: darkolivegreen">${resultRow.boardCode}</p>
        <p style="color: darkolivegreen">${resultRow.postTitle}</p>
        <p style="color: darkolivegreen">${resultRow.veiwCnt}</p>
        <p style="color: darkolivegreen">${resultRow.isPinned}</p>
    </c:forEach>
</div>=
</body>
</html>
