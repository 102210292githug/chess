<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CHESS ONLINE - INFOMATION</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleInfomation.css'/>" />
<script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>

<body>
<div id="divMain">
    <div id="left">
    <div id="left-top">
        <button id="home" class="buttonTop"><i style="color: rgb(104, 169, 243);" class="fa-solid fa-house"></i>CHESS ONLINE</button>
        <button id="play" class="buttonTop"><i class="fa-solid fa-chess"></i>PLAY</button>
        <button id="analysis" class="buttonTop"><i class="fa-solid fa-chess-board"></i>ANALYSIS</button>
        <button id="history" class="buttonTop"><i class="fa-solid fa-clock"></i>HISTORY</button>
    </div>

    <div id="left-bot">
        <button class="buttonBot"><i class="fa-solid fa-circle-half-stroke"></i>Dark UI</button>
        <button class="buttonBot"><i class="fa-solid fa-arrow-left"></i>Collapse</button>
        <button class="buttonBot"><i class="fa-solid fa-gear"></i>Settings</button>
        <button class="buttonBot"><i class="fa-solid fa-circle-info"></i>Helps</button>
    </div>
    </div>

    <div id="contentSettings">
        <div id="settings">
            <i style="padding-top: 17%; padding-right: 3%;" class="fa-solid fa-gear"></i><p>SETTINGS</p>
        </div>
        <div id="content">
            <div id="contentLeft">
                <button class="buttonContentLeft" id="profile"><i class="fa-regular fa-user"></i>Profile</button>
                <button class="buttonContentLeft" id="password"><i class="fa-solid fa-key"></i>Password</button>
                <button class="buttonContentLeft" id="more">... More</button>
            </div>
            <div id="contentRight">
                <div id="contentRightTop">
                    <img id="user" src="/template/web/Icon/Icon_.png" alt="">
                    <div id="info1st">
                        <p id="you">You</p>
                        <p id="elo">Elo: 000</p>
                        <div style="display: flex;">
                            <p id="editStatus">Edit Status</p>
                            <a style="margin-left: 20px; padding-top: 26px;" href=""><i class="fa-solid fa-pen-to-square"></i></a>
                        </div>
                        
                    </div>
                </div>

                <div id="contentRightBot">
                    <div id="contentRightBotInf">
                        <div class="rightBotInf" id="userName">
                            <p class="nameInfUser">Username</p>
                            <p style="margin-left: 108px;" class="infoUser">Nhom3pbl</p>
                        </div>

                        <div class="rightBotInf" id="firstName">
                            <p class="nameInfUser">First Name</p>
                            <input style="margin: 0 0 10px 100px; width: 250px;" class="infoUser" type="text" value="Admin">
                        </div>

                        <div class="rightBotInf" id="lastName">
                            <p class="nameInfUser">Last Name</p>
                            <input style="margin: 0 0 10px 102px; width: 250px;" class="infoUser" class="infoUser" type="text" value="Admin">
                        </div>

                        <div class="rightBotInf" id="email">
                            <p class="nameInfUser">Email</p>
                            <p style="margin-left: 135px;" class="infoUser">nhom3pbl@gmail.com</p>
                            <a id="change" href="">Change</a>
                        </div>

                        <div class="rightBotInf" id="location">
                            <p class="nameInfUser">Location</p>
                            <input style="margin-left: 115px; width: 250px;" class="infoUser" type="text" name="" id="">
                        </div>

                        <button id="save">SAVE</button>
                    </div>                    
                </div>
            </div>
        </div>
    </div>
</div> 
</body>
</html>
