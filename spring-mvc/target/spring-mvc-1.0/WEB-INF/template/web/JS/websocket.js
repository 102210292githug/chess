//var initialized = false;
var ws = new WebSocket("ws://localhost:8080/spring-mvc/websocket");
document.addEventListener("DOMContentLoaded", function() {
    // if (initialized) return;
    // initialized = true;

    

    ws.onopen = function(event) {
        console.log("WebSocket connection opened:", event);
    };


    ws.onclose = function(event) {
        console.log("WebSocket connection closed:", event);
    };
    document.querySelector('#playOnline').addEventListener('click', () => {
        ws.send('play');
    });
    


});

ws.onmessage = function(event) {
    var message = event.data;
    var isW = true;
    console.log(message);
    if (message == "WHITE") {
        // Người chơi này là người chơi màu trắng
    	KhoiTao();
        WhiteOrBlack(isW);
    } else if (message == "BLACK") {
        // Người chơi này là người chơi màu đen
    	KhoiTao();
        WhiteOrBlack(!isW);
    } 
    else if(message == "YOU LOSE"){
    	alert(message);
    	KhoiTao();
    }
    else {
        // Xử lý các nước đi của đối thủ
    	
        var parts = message.split("-");
        console.log(parts[0] + " " + parts[1]);
        handleOpponentMove(parts[0], parts[1]);
        Nextturn();
    }
};

function endGame(){
	ws.send("YOU LOSE");
}


function sendMove(fromLocation, toLocation) {
	var move = fromLocation +  + toLocation;
	ws.send(move);
        
}
