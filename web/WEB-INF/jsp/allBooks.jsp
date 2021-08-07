<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Morty
  Date: 2021/8/4
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script>
        del = function (){
            const msg = "您真的确定要删除吗？\n\n";
            if (confirm(msg)===true){
                return true;
            }else{
                return false;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表</small>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/allBooks">显示全部书籍</a>
            </div>
            <div class="col-md-6 column">
                <form action="${pageContext.request.contextPath}/book/selectByName" method="get" class="form-inline">
                    <input type="text" class="form-control" name="bookName" placeholder="查询的书籍名称">
                    <input type="submit" class="btn btn-primary" value="查询">
                </form>
            </div>
            <div class="col-md-2 column">
                <a class="btn btn-primary btn-center" href="${pageContext.request.contextPath}/logout">注销登录</a>
            </div>
        </div>
    </div>
    <div class="col-md-12 column">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>书籍编号</th>
                <th>书籍名</th>
                <th>书籍数量</th>
                <th>书籍详情</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.bookID}</td>
                    <td>${book.bookName}</td>
                    <td>${book.bookCount}</td>
                    <td>${book.detail}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/book/toUpdate?id=${book.bookID}">修改</a>&nbsp;|
                        <a href="${pageContext.request.contextPath}/book/deleteBook?id=${book.bookID}" onclick="return del()">删除</a>&nbsp;|
                        <a href="${pageContext.request.contextPath}/book/view?id=${book.bookID}">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
