<%--
  Created by IntelliJ IDEA.
  User: sharo
  Date: 2025-06-22
  Time: 오후 5:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
  <title>Sha_Jang_Tumbler</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-VTmh+5lDQgxBgaA8cD3X2iKQk4YI3sYeEjwA0kaOK1Z3XM3+o2D4w9abEzoS4V6L"
        crossorigin="anonymous"/>

  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"/>
  <link rel="stylesheet" href="/static/css/globals.css" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<main class="max-w-md mx-auto px-4 py-16">
  <div class="bg-white border border-beige-200 shadow-lg rounded-lg">
    <div class="text-center p-6 border-b border-beige-100">
      <h1 class="text-2xl font-light text-gray-900 mb-2">로그인</h1>
      <p class="text-beige-600">계정에 로그인하여 쇼핑을 시작하세요</p>
    </div>
    <div class="p-6 space-y-4">
      <c:if test="${not empty errorMsg}">
        <p style="color:red">${errorMsg}</p>
      </c:if>
      <form action="/login" method="post" id="login">
        <div class="space-y-2 mb-4">
          <label for="email" class="block text-gray-900 font-medium">이메일</label>
          <input type="email" id="email" name="userEmail" placeholder="your@email.com"
                 class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
        </div>
        <div class="space-y-2 mb-6">
          <label for="password" class="block text-gray-900 font-medium">비밀번호</label>
          <input type="password" id="password" name="userPwd"
                 class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
        </div>

        <button type="submit" class="w-full bg-gray-900 hover:bg-beige-800 text-white py-3 rounded-lg transition-colors">
          로그인
        </button>
      </form>
      <div class="text-center space-y-2">
        <p class="text-sm text-beige-600">
          계정이 없으신가요?
          <a href="/register" class="text-gray-900 hover:underline">회원가입</a>
        </p>
      </div>
    </div>
  </div>
</main>
<script>
  document.getElementById("login").addEventListener("submit", function (e) {
    const email = document.getElementById("email").value;
    const pwd = document.getElementById("password").value;

    if(!email.trim()){
      e.preventDefault();
      alert("이메일을 입력해주세요."); return;
    }
    if(!pwd.trim()){
      e.preventDefault();
      alert("비밀번호를 입력해주세요."); return;
    }

  });
</script>



<jsp:include page="../common/footer.jsp" />
</body>
</html>
