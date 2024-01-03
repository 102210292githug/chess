<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CHESS ONLINE</title>

<link rel="icon" href="<c:url value='/template/web/QuanCo/Ma_Do.png'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/style.css'/>" />
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
				<button id="analysis" class="buttonTop">
					<i class="fa-solid fa-chess-board"></i>ANALYSIS
				</button>
				<button id="history" class="buttonTop">
					<i class="fa-solid fa-clock"></i>HISTORY
				</button>
			</div>

			<div id="left-bot">
				<button class="buttonBot">
					<i class="fa-solid fa-circle-half-stroke"></i>Dark UI
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-arrow-left"></i>Collapse
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-gear"></i>Settings
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-circle-info"></i>Helps
				</button>
			</div>
		</div>

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
						<td id="81" onclick="Click(this.id)"><img id="i81" /></td>
						<td id="82" onclick="Click(this.id)"><img id="i82" /></td>
						<td id="83" onclick="Click(this.id)"><img id="i83" /></td>
						<td id="84" onclick="Click(this.id)"><img id="i84" /></td>
						<td id="85" onclick="Click(this.id)"><img id="i85" /></td>
						<td id="86" onclick="Click(this.id)"><img id="i86" /></td>
						<td id="87" onclick="Click(this.id)"><img id="i87" /></td>
						<td id="88" onclick="Click(this.id)"><img id="i88" /></td>
					</tr>

					<tr>
						<td id="71" onclick="Click(this.id)"><img id="i71" /></td>
						<td id="72" onclick="Click(this.id)"><img id="i72" /></td>
						<td id="73" onclick="Click(this.id)"><img id="i73" /></td>
						<td id="74" onclick="Click(this.id)"><img id="i74" /></td>
						<td id="75" onclick="Click(this.id)"><img id="i75" /></td>
						<td id="76" onclick="Click(this.id)"><img id="i76" /></td>
						<td id="77" onclick="Click(this.id)"><img id="i77" /></td>
						<td id="78" onclick="Click(this.id)"><img id="i78" /></td>
					</tr>
					<tr>
						<td id="61" onclick="Click(this.id)"><img id="i61" /></td>
						<td id="62" onclick="Click(this.id)"><img id="i62" /></td>
						<td id="63" onclick="Click(this.id)"><img id="i63" /></td>
						<td id="64" onclick="Click(this.id)"><img id="i64" /></td>
						<td id="65" onclick="Click(this.id)"><img id="i65" /></td>
						<td id="66" onclick="Click(this.id)"><img id="i66" /></td>
						<td id="67" onclick="Click(this.id)"><img id="i67" /></td>
						<td id="68" onclick="Click(this.id)"><img id="i68" /></td>
					</tr>


					<tr>
						<td id="51" onclick="Click(this.id)"><img id="i51" /></td>
						<td id="52" onclick="Click(this.id)"><img id="i52" /></td>
						<td id="53" onclick="Click(this.id)"><img id="i53" /></td>
						<td id="54" onclick="Click(this.id)"><img id="i54" /></td>
						<td id="55" onclick="Click(this.id)"><img id="i55" /></td>
						<td id="56" onclick="Click(this.id)"><img id="i56" /></td>
						<td id="57" onclick="Click(this.id)"><img id="i57" /></td>
						<td id="58" onclick="Click(this.id)"><img id="i58" /></td>
					</tr>
					<tr>
						<td id="41" onclick="Click(this.id)"><img id="i41" /></td>
						<td id="42" onclick="Click(this.id)"><img id="i42" /></td>
						<td id="43" onclick="Click(this.id)"><img id="i43" /></td>
						<td id="44" onclick="Click(this.id)"><img id="i44" /></td>
						<td id="45" onclick="Click(this.id)"><img id="i45" /></td>
						<td id="46" onclick="Click(this.id)"><img id="i46" /></td>
						<td id="47" onclick="Click(this.id)"><img id="i47" /></td>
						<td id="48" onclick="Click(this.id)"><img id="i48" /></td>
					</tr>
					<tr>
						<td id="31" onclick="Click(this.id)"><img id="i31" /></td>
						<td id="32" onclick="Click(this.id)"><img id="i32" /></td>
						<td id="33" onclick="Click(this.id)"><img id="i33" /></td>
						<td id="34" onclick="Click(this.id)"><img id="i34" /></td>
						<td id="35" onclick="Click(this.id)"><img id="i35" /></td>
						<td id="36" onclick="Click(this.id)"><img id="i36" /></td>
						<td id="37" onclick="Click(this.id)"><img id="i37" /></td>
						<td id="38" onclick="Click(this.id)"><img id="i38" /></td>
					</tr>
					<tr>
						<td id="21" onclick="Click(this.id)"><img id="i21" /></td>
						<td id="22" onclick="Click(this.id)"><img id="i22" /></td>
						<td id="23" onclick="Click(this.id)"><img id="i23" /></td>
						<td id="24" onclick="Click(this.id)"><img id="i24" /></td>
						<td id="25" onclick="Click(this.id)"><img id="i25" /></td>
						<td id="26" onclick="Click(this.id)"><img id="i26" /></td>
						<td id="27" onclick="Click(this.id)"><img id="i27" /></td>
						<td id="28" onclick="Click(this.id)"><img id="i28" /></td>
					</tr>

					<tr>
						<td id="11" onclick="Click(this.id)"><img id="i11" /></td>
						<td id="12" onclick="Click(this.id)"><img id="i12" /></td>
						<td id="13" onclick="Click(this.id)"><img id="i13" /></td>
						<td id="14" onclick="Click(this.id)"><img id="i14" /></td>
						<td id="15" onclick="Click(this.id)"><img id="i15" /></td>
						<td id="16" onclick="Click(this.id)"><img id="i16" /></td>
						<td id="17" onclick="Click(this.id)"><img id="i17" /></td>
						<td id="18" onclick="Click(this.id)"><img id="i18" /></td>
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