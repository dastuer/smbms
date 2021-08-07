<%--
  Created by IntelliJ IDEA.
  User: Morty
  Date: 2021/8/6
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限不足</title>
</head>
<body>
<h2>权限不足</h2>
<h3>修改更新需要管理员权限</h3>
<h4><a href="${pageContext.request.contextPath}/goLogin">重新登录</a></h4>
<h4><a href="${pageContext.request.contextPath}/book/allBooks">回到首页</a></h4>
</body>
</html>
