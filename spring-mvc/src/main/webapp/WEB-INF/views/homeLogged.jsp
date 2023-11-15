<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CHESS ONLINE</title>

<link rel="icon"
	href="<c:url value='/template/web/QuanCo/Ma_Trang.png'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleHomeLogged.css'/>" />
<script src="https://kit.fontawesome.com/5175756225.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/Run.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/Sources.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/KhoiTao.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/Function.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Tot.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Vua.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Xe.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Tuong.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Ma.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Hau.js'/>"></script>
<!-- <script src="path_to_your_js_directory/websocket.js"></script> -->
<script src="<c:url value='/template/web/JS/websocket.js'/>"></script>



</head>
<body onload="KhoiTao()">
	<div id="divMain">
		<div id="left">
			<div id="left-top">
				<button id="home" class="buttonTop">
					<i style="color: rgb(104, 169, 243);" class="fa-solid fa-house"></i>CHESS
					ONLINE
				</button>
				<button id="play" class="buttonTop">
					<i class="fa-solid fa-chess"></i>PLAY</button>
				<button id="analysis" class="buttonTop">
					<i class="fa-solid fa-chess-board"></i>ANALYSIS
				</button>
				<button id="history" class="buttonTop">
					<i class="fa-solid fa-clock"></i>HISTORY
				</button>
			</div>

				  <div id="left-bot">
				    <button class="buttonBot">
				    	<i class="fa-solid fa-circle-half-stroke"></i>Dark UI</button>
				    <button class="buttonBot">
				    	<i class="fa-solid fa-arrow-left"></i>Collapse</button>
				    <button id="settingsButton" class="buttonBot">
				    	<i class="fa-solid fa-gear"></i>Settings</button>
				    <button class="buttonBot">
				    	<i class="fa-solid fa-circle-info"></i>Helps</button>
				  </div>
				</div>
				
				<div id="settingsChoose">
				  <button id="logOut" class="buttonSettingsChoose">Log out</button>
				  <br>
				  <button id="profile" class="buttonSettingsChoose">Profie</button>
				</div>
				
				<script>
				  // Lấy tham chiếu đến button và đối tượng cần thay đổi
				  const button = document.getElementById('settingsButton');
				  const settingsChoose = document.getElementById('settingsChoose');
				
				  // Thêm sự kiện hover
				  button.addEventListener('mouseover', () => {
				    settingsChoose.style.display = 'block';
				  });
				
				  button.addEventListener('mouseout', () => {
				    settingsChoose.style.display = 'none';
				  });
				</script>

		<div id="mid">
			<div id="divNguoiChoiCoDen">
				<table id="tblNguoiCoDen">
					<tr>
						<td><img id="iCoDen"
							src="<c:url value='/template/web/User/User_Enb.png'/>" alt="" /></td>

					</tr>
					<tr>
						<td id="PointCoDen">Point: 0</td>
					</tr>
				</table>

			</div>

			<div id="divBanCo">
				<table id="BanCo">
					<tr>
						<td id="a8" onclick="Click(this.id)"><img id="ia8" /></td>
						<td id="b8" onclick="Click(this.id)"><img id="ib8" /></td>
						<td id="c8" onclick="Click(this.id)"><img id="ic8" /></td>
						<td id="d8" onclick="Click(this.id)"><img id="id8" /></td>
						<td id="e8" onclick="Click(this.id)"><img id="ie8" /></td>
						<td id="f8" onclick="Click(this.id)"><img id="if8" /></td>
						<td id="g8" onclick="Click(this.id)"><img id="ig8" /></td>
						<td id="h8" onclick="Click(this.id)"><img id="ih8" /></td>
					</tr>
					<tr>
						<td id="a7" onclick="Click(this.id)"><img id="ia7" /></td>
						<td id="b7" onclick="Click(this.id)"><img id="ib7" /></td>
						<td id="c7" onclick="Click(this.id)"><img id="ic7" /></td>
						<td id="d7" onclick="Click(this.id)"><img id="id7" /></td>
						<td id="e7" onclick="Click(this.id)"><img id="ie7" /></td>
						<td id="f7" onclick="Click(this.id)"><img id="if7" /></td>
						<td id="g7" onclick="Click(this.id)"><img id="ig7" /></td>
						<td id="h7" onclick="Click(this.id)"><img id="ih7" /></td>
					</tr>
					<tr>
						<td id="a6" onclick="Click(this.id)"><img id="ia6" /></td>
						<td id="b6" onclick="Click(this.id)"><img id="ib6" /></td>
						<td id="c6" onclick="Click(this.id)"><img id="ic6" /></td>
						<td id="d6" onclick="Click(this.id)"><img id="id6" /></td>
						<td id="e6" onclick="Click(this.id)"><img id="ie6" /></td>
						<td id="f6" onclick="Click(this.id)"><img id="if6" /></td>
						<td id="g6" onclick="Click(this.id)"><img id="ig6" /></td>
						<td id="h6" onclick="Click(this.id)"><img id="ih6" /></td>
					</tr>
					<tr>
						<td id="a5" onclick="Click(this.id)"><img id="ia5" /></td>
						<td id="b5" onclick="Click(this.id)"><img id="ib5" /></td>
						<td id="c5" onclick="Click(this.id)"><img id="ic5" /></td>
						<td id="d5" onclick="Click(this.id)"><img id="id5" /></td>
						<td id="e5" onclick="Click(this.id)"><img id="ie5" /></td>
						<td id="f5" onclick="Click(this.id)"><img id="if5" /></td>
						<td id="g5" onclick="Click(this.id)"><img id="ig5" /></td>
						<td id="h5" onclick="Click(this.id)"><img id="ih5" /></td>
					</tr>
					<tr>
						<td id="a4" onclick="Click(this.id)"><img id="ia4" /></td>
						<td id="b4" onclick="Click(this.id)"><img id="ib4" /></td>
						<td id="c4" onclick="Click(this.id)"><img id="ic4" /></td>
						<td id="d4" onclick="Click(this.id)"><img id="id4" /></td>
						<td id="e4" onclick="Click(this.id)"><img id="ie4" /></td>
						<td id="f4" onclick="Click(this.id)"><img id="if4" /></td>
						<td id="g4" onclick="Click(this.id)"><img id="ig4" /></td>
						<td id="h4" onclick="Click(this.id)"><img id="ih4" /></td>
					</tr>
					<tr>
						<td id="a3" onclick="Click(this.id)"><img id="ia3" /></td>
						<td id="b3" onclick="Click(this.id)"><img id="ib3" /></td>
						<td id="c3" onclick="Click(this.id)"><img id="ic3" /></td>
						<td id="d3" onclick="Click(this.id)"><img id="id3" /></td>
						<td id="e3" onclick="Click(this.id)"><img id="ie3" /></td>
						<td id="f3" onclick="Click(this.id)"><img id="if3" /></td>
						<td id="g3" onclick="Click(this.id)"><img id="ig3" /></td>
						<td id="h3" onclick="Click(this.id)"><img id="ih3" /></td>
					</tr>
					<tr>
						<td id="a2" onclick="Click(this.id)"><img id="ia2" /></td>
						<td id="b2" onclick="Click(this.id)"><img id="ib2" /></td>
						<td id="c2" onclick="Click(this.id)"><img id="ic2" /></td>
						<td id="d2" onclick="Click(this.id)"><img id="id2" /></td>
						<td id="e2" onclick="Click(this.id)"><img id="ie2" /></td>
						<td id="f2" onclick="Click(this.id)"><img id="if2" /></td>
						<td id="g2" onclick="Click(this.id)"><img id="ig2" /></td>
						<td id="h2" onclick="Click(this.id)"><img id="ih2" /></td>
					</tr>
					<tr>
						<td id="a1" onclick="Click(this.id)"><img id="ia1" /></td>
						<td id="b1" onclick="Click(this.id)"><img id="ib1" /></td>
						<td id="c1" onclick="Click(this.id)"><img id="ic1" /></td>
						<td id="d1" onclick="Click(this.id)"><img id="id1" /></td>
						<td id="e1" onclick="Click(this.id)"><img id="ie1" /></td>
						<td id="f1" onclick="Click(this.id)"><img id="if1" /></td>
						<td id="g1" onclick="Click(this.id)"><img id="ig1" /></td>
						<td id="h1" onclick="Click(this.id)"><img id="ih1" /></td>
					</tr>
				</table>

			</div>

			<div id="divNguoiChoiCoDo">
				<table id="tblNguoiChoiCoDo">
					<tr>
						<td><img id="iCoDo"
							src="<c:url value='/template/web/User/User_Dis.png'/>" alt="" /></td>
						<!-- <td><img id="iCoDo" src="User/User_Dis.png" /></td> -->
					</tr>
					<tr>
						<td id="PointCoDo">Point: 0</td>
					</tr>
				</table>
			</div>
		</div>

		<div id="right">
			<div id="edit-Right">
				<p class="inRight" id="playChess">
					PLAY CHESS <br /> <i style="color: black;"
						class="fa-solid fa-chess"></i>
				</p>
				<button class="inRight" id="playOnline">
					<i class="fa-solid fa-bolt"></i>Play Online
				</button>
				<button class="inRight" id="playComputer">
					<i class="fa-solid fa-computer"></i>Computer
				</button>
				<button class="inRight" id="playFriend">
					<i class="fa-solid fa-user-group"></i>Play with Friend
				</button>
			</div>
		</div>

	</div>

</body>
</html>