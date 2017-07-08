<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 08.07.17
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style type="text/css">
        * {
            background-color: #00baff;
        }
        .button {
            color: #b1b1b1;
            background-color: #ffffff;
        }
        .form {
            padding: 50px;
            position: fixed; top: 40%; left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }
    </style>
    <style>
        .enjoy-css {
            display: inline-block;
            -webkit-box-sizing: content-box;
            -moz-box-sizing: content-box;
            box-sizing: content-box;
            padding: 6px 20px;
            border: none;
            -webkit-border-radius: 32px / 54px;
            border-radius: 32px / 54px;
            font: normal 18px/normal "Coda", Helvetica, sans-serif;
            color: rgba(0,142,198,1);
            -o-text-overflow: ellipsis;
            text-overflow: ellipsis;
            background: rgba(252,252,252,1);
            -webkit-box-shadow: 0 1px 2px 0 rgba(0,0,0,0.2) inset;
            box-shadow: 0 1px 2px 0 rgba(0,0,0,0.2) inset;
            text-shadow: 1px 1px 0 rgba(255,255,255,0.66) ;
            -webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            -moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            -o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
        }

        .enjoy-css:hover {
            background: rgba(232,249,255,1);
            -webkit-box-shadow: 0 2px 2px 0 rgba(90,90,90,0.2) inset;
            box-shadow: 0 2px 2px 0 rgba(90,90,90,0.2) inset;
        }

        .enjoy-css:focus {
            background: rgba(255,253,232,1);
        }
    </style>
</head>
<body>

<div class="form">

    <h1>Вход в систему</h1><br>
    <form method="post" action="">

        <input class="enjoy-css" type="text" required placeholder="login" name="login"><br>
        <input class="enjoy-css" type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Войти">

    </form>
    <c:out value="${warning}"/>

</div>

</body>
</html>
