<%--
  Created by IntelliJ IDEA.
  User: Morty
  Date: 2021/8/4
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>
      #all{
        text-decoration: none;
        color: black;
        font-size: 18px;
      }
      h3{
        margin: 100px auto;
        width: 180px;
        height: 38px;
        text-align: center;
        line-height: 38px;
        background: blueviolet;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
  <h3>
    <a href="${pageContext.request.contextPath}/goLogin">登录</a>
<%--    <a href="./book/allBooks" id="all">查看所有书籍</a>--%>
  </h3>
  </body>
</html>
