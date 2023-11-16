<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CHESS ONLINE - HISTORY</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleHistory.css'/>" />
<link rel="icon"
	href="<c:url value='/template/web/QuanCo/Ma_Trang.png'/>" />
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
				<button class="buttonBot">
					<i class="fa-solid fa-circle-half-stroke"></i>Dark UI
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-arrow-left"></i>Collapse
				</button>
				<button id="settingsButton" class="buttonBot">
					<i class="fa-solid fa-gear"></i>Settings
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-circle-info"></i>Helps
				</button>
			</div>
		</div>
		
					<div id="settingsChoose">
			  <button id="logOut" class="buttonSettingsChoose">Log out</button>
			  <br>
			  <button id="profile" class="buttonSettingsChoose" >Profie</button>
			</div>
			
			<script>
			  // Lấy tham chiếu đến button và đối tượng cần thay đổi
			  const button = document.getElementById('settingsButton');
			  const settingsChoose = document.getElementById('settingsChoose');
			  let timeoutId;
			
			  // Thêm sự kiện hover
			  button.addEventListener('mouseover', () => {
			    settingsChoose.style.display = 'block';
			    clearTimeout(timeoutId); // Xóa bất kỳ timeout nào đang đợi
			  });
			
			  button.addEventListener('mouseout', () => {
			    // Sử dụng setTimeout để chờ 2 giây trước khi ẩn
			    timeoutId = setTimeout(() => {
			      settingsChoose.style.display = 'none';
			    }, 2000);
			  });
			</script>

        <div id="right">
          <div id="historyTitle">
            <i style="padding-top: 17%; padding-right: 3%;" class="fa-solid fa-clock"></i><p>HISTORY</p>
          </div>
          <div id="historyContent">
            <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitle">WIN</h1>
                <p class="pointElo">Your elo: +999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
              </div>
            </div>

            <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitleLose">LOSE</h1>
                <p class="pointEloLose">Your elo: -999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
              </div>
            </div>
            
            <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitleLose">LOSE</h1>
                <p class="pointEloLose">Your elo: -999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
              </div>
            </div>
            
            <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitleLose">LOSE</h1>
                <p class="pointEloLose">Your elo: -999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
              </div>
            </div>
            
                <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitle">WIN</h1>
                <p class="pointElo">Your elo: +999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
              </div>
            </div>
            
            <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitleLose">LOSE</h1>
                <p class="pointEloLose">Your elo: -999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
              </div>
            </div>
            
            <div class="boxHistoryContent">
              <div class="userA">
                <img class="colorUser" src="./template/web/QuanCo/Tot_Den.png" alt="">
                <div class="infoUser">
                  <div class="infoUserName">You</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">
              </div>
              <div class="result">
                <h1 class="resultTitleLose">LOSE</h1>
                <p class="pointEloLose">Your elo: -999</p>
              </div>
              <div class="userB">
                <img style="width: 50px;" src="./template/web/Icon/la-co-viet-nam-vector-1.png" alt="">     
                <div style="margin: 0px 10px 0px 20px;" class="infoUser">
                  <div class="infoUserName">Huy</div>
                  <div class="infoUserElo">Elo: 0</div>
                </div>
                <img class="colorUser" src="./template/web/QuanCo/Tot_Trang.png" alt="">
              </div>
            </div>
          </div>         
        </div>      
    </div>
</body>
</html>