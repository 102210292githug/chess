<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CHESS ONLINE - LOG IN</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleLogin.css'/>" />
<link rel="icon"
	href="<c:url value='/template/web/QuanCo/Ma_Trang.png'/>" />	
	
<body>
    <div id="content">
        <h1 id="contentLogin">CHESS ONLINE</h1>
        <div id="boxLogin">
            <form id="formBoxLogin" action="">
                <input  class="inputInfo" type="text" placeholder="User name" name="" id="">
                <input class="inputInfo" type="password" placeholder="Password" name="" id="">
                <div id="rememberMe">
                    <input style="width: 20px;" type="checkbox" name="" id="">
                    <p style="color: #b78c8c;">Remember me</p>
                    <a id="forgotPassword" href="">Forgot Password</a>
                </div>
                <button id="login">Log In</button>
                <hr>
                <a style="margin-top: 10px; color: #3cbbea;" href="">Sign up - and star playing chess!</a>
            </form>
        </div>
    </div>
</body>
</html>