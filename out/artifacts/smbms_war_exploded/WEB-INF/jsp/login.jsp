<%--
  Created by IntelliJ IDEA.
  User: Morty
  Date: 2021/8/6
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--图标样式-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

    <!--布局框架-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/util.css">

    <!--主要样式-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css">

    <style>
        input[type="checkbox"] {
            width: 20px;
            height: 20px;
            display: inline-block;
            text-align: center;
            vertical-align: middle;
            line-height: 18px;
            margin-right: 10px;
            position: relative;
        }

        input[type="checkbox"]::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            background: #fff;
            width: 100%;
            height: 100%;
            border: 1px solid #d9d9d9;
            border-radius: 4px;
        }

        input[type="checkbox"]:checked::before {
            content: "\2713";
            background-color: #fff;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            border: 1px solid #7D7D7D;
            border-radius:4px;
            color: #7D7D7D;
            font-size: 15px;
            font-weight: bold;
        }
        .men1{
            margin-bottom: 3px;
            height: 16px;
            width: 100px;
            font-size: 15px;
            position: relative;
            top: 1px;
        }
        .checkBox{
            position: relative;
            left: 15px;
        }
    </style>
</head>

<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('${pageContext.request.contextPath}/static/images/img-01.jpg');">
        <div class="wrap-login100 p-t-34 p-b-41">
            <form class="login100-form validate-form" action="${pageContext.request.contextPath}/login" method="post" onsubmit="return nameValidate()&&pwdValidate()">
                <div class="login100-form-avatar">
                    <img src="${pageContext.request.contextPath}/static/images/img_1.png"  alt="AVATAR">
                </div>
                <span class="login100-form-title p-t-34 p-b-41">欢迎来到图书管理系统</span>
                <div class="wrap-input100 validate-input m-b-20" data-validate="请输入用户名">
                    <input class="input100" type="text" name="username" placeholder="用户名" autocomplete="off" id="usr" onblur="nameValidate()" required>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100"><i class="fa fa-user"></i></span>
                </div>
                <div class="wrap-input100 validate-input m-b-10" data-validate="请输入密码">
                    <input class="input100" type="password" name="pwd" placeholder="密码" id="pwd" onblur="pwdValidate()" required>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100"><i class="fa fa-lock"></i></span>
                </div>
                <div class="checkBox"><label><input type="checkbox" name="rem">记住密码</label></div>
                <div class="men1"><span id="wif"></span></div>
                <div class="container-login100-form-btn p-t-10">
                    <input type="submit" class="login100-form-btn" value="登录"/>
                </div>
                <div class="text-center w-full"></div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>

<script>
    function nameValidate(){

        let flag;
        $.post(
            {
                url:"${pageContext.request.contextPath}/getState",
                //此处需要注意的是要想获取ajax返回的值这个async属性必须设置成同步的，否则获取不到返回值
                async : false,
                data:{"username":$("#usr").val()},
                success:function (data){
                    data=JSON.parse(data);
                    let  uif=$("#wif");
                    if (data.uif==="ok"){
                        uif.css("color","green");
                        uif.html("OK");
                        flag = true;
                    }
                    else if (data.uif==="none"){
                        uif.css("color","red");
                        uif.html("用户不存在");
                        flag = false;
                    }
                }});
        return flag;
    }
    function pwdValidate(){
        let flag;
         $.post(
            {
                url:"${pageContext.request.contextPath}/getState",
                async : false,
                data:{"username":$("#usr").val(),"pwd":$("#pwd").val()},
                success:function (data){
                    data=JSON.parse(data);
                    let  pif=$("#wif");
                    if (data.pif==="ok"){
                        pif.css("color","green");
                        pif.html("OK");
                        flag= true;
                    }
                    else if (data.pif==="wrong"){
                        pif.css("color","red");
                        pif.html("密码错误");
                        flag =  false;
                    }
                }});
         return flag;
    }
    window.onload=function(){
        let msg = "${info}";
        if (msg.length > 0){
            if (msg==="none"){
                $("#wif").html("用户不存在").css("color","red");
            }
            else if (msg==="wrong"){
                $("#wif").html("密码错误").css("color","red");
            }
        }
    }
</script>
</body>
</html>

