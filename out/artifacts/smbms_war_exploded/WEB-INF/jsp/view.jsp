<%--
  Created by IntelliJ IDEA.
  User: Morty
  Date: 2021/8/4
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>update book</title>
  <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>
  <script type="text/javascript">
    window.onload=function (){
      let coverImgUrl="${book.cover}"
      if (coverImgUrl.length===0){
        $("#coverImg").html("未上传").css("color","red");
      }
    }
  </script>
</head>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="page-header">
        <h1 class="text-center">
          <small>书籍详情</small>
        </h1>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-4 column col-md-offset-1">
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/allBooks">返回</a>
    </div>
    <br>
    <br>
    <div class="col-md-12 column">
      <table class="table table-hover">
        <tbody  class="text-center">
        <tr>
          <td class="col-md-6 column">书籍编号</td>
          <td class=>${book.bookName}</td>
        </tr>
        <tr>
          <td>书籍数量</td>
          <td>${book.bookCount}</td>
        </tr>
        <tr>
          <td>书籍说明</td>
          <td>${book.detail}</td>
        </tr>
        <tr>
          <td>封面</td>
          <td id="coverImg"><img src="${book.cover}" alt="#" height="200" width="300"></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>


