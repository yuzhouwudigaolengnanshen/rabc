<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/img/favicon.ico">

    <title>登录</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="text"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
    <script>
        function changeImage() {
            document.getElementById("image1").src  = "/captcha?t="+new Date().getTime();
        }
        function verify() {
            var v = document.getElementById("aaa").value;
            var xhr = new XMLHttpRequest();
            xhr.onload = function() {
                // x 是结果 取值 true | false
                var x = JSON.parse(xhr.responseText);
                if(x) {
                    document.getElementById("error").innerText="验证成功";
                } else {
                    document.getElementById("error").innerText="验证失败";
                }
            };
            xhr.open("get","/checkCaptcha?captcha="+v, true);
            xhr.send();
        }
    </script>
</head>

<body>

<div class="container">

    <form class="form-signin" action="/login" method="post">
        <h3 class="form-signin-heading">请登录</h3>
        <label for="inputUsername" class="sr-only">用户名</label>
        <input type="text" name="username" id="inputUsername" class="form-control" placeholder="请输入用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" value="123" required>
        <label for="aaa" class="sr-only">验证码</label>
        <input type="text" id="aaa" name="captcha" onkeyup="verify()"> <span id="error"></span>
        <img src="/captcha" onclick="changeImage()" id="image1"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

        <hr/>
        <c:if test="${not empty error}">
        <div class="alert alert-warning" role="alert">
            <h5 class="alert-heading">错误!</h5>
            <p>${error}</p>
        </div>
        </c:if>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug
<script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
-->
</body>
</html>