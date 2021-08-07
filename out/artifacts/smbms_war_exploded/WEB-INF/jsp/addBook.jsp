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
    <title>update book</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script>
        function checkType(){
            if (document.getElementById("cover").value.length===0){
                return true;
            }
            //得到上传文件的值
            const fileName = document.getElementById("cover").value;
            //返回String对象中子字符串最后出现的位置.
            const seat = fileName.lastIndexOf(".");
            //返回位于String对象中指定位置的子字符串并转换为小写.
            const extension = fileName.substring(seat).toLowerCase();
            //判断允许上传的文件格式
            //if(extension!=".jpg"&&extension!=".jpeg"&&extension!=".gif"&&extension!=".png"&&extension!=".bmp"){
            //alert("不支持"+extension+"文件的上传!");
            //return false;
            //}else{
            //return true;
            //}
            const allowed = [".jpg", ".gif", ".png", ".jpeg"];
            for(let i=0; i<allowed.length; i++){
                if(!(allowed[i]!==extension)){
                    return true;
                }
            }
            alert("不支持"+extension+"格式");
            return false;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>添加书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/addBook" method="post" enctype="multipart/form-data" onsubmit="return checkType()">
        <div class="form-group">
            <label for="name">书籍名称</label>
            <input type="text" class="form-control" id="name" name="bookName" required>
        </div>
        <div class="form-group">
            <label for="number">书籍数量</label>
            <input type="number" class="form-control" id="number" name="bookCount"  required>
        </div>
        <div class="form-group">
            <label for="detail">书籍说明</label>
            <input type="text" class="form-control" id="detail" name="detail" required>
        </div>
        <div class="form-group">
            <label for="cover">封面图</label>
            <input class="form-control" id="cover" name="file"  type="file">
        </div>
        <button type="submit" class="btn btn-default">提交</button>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/book/allBooks">返回</a>
    </form>
</div>
</body>
</html>
