// websocket-connection.js
var socket;

function openWebSocket(userID) {
	KhoiTao();
	WhiteOrBlack(true);
	socket = new WebSocket("ws://localhost:8080/spring-mvc/websocket?userID=" + userID);
	console.log(userID);
	socket.onopen = function() {
		console.log("WebSocket connection established");
	};

	socket.onmessage = function(event) {
		console.log("Message from server:", event.data);
		var message = event.data;
		console.log(message);
		if (message == "WHITE") {
	        WhiteOrBlack(true);
	    } else if (message == "BLACK") {
	        WhiteOrBlack(false);
	    } 
	    
	    else {
			var isW = true;
			
	
			var data = message.split(' AND ');
			
			var parts = data[0];
			parseDataString(data[1]);
			console.log(legal);
			addMoveToList(parts);
			handleOpponentMove(parts[0] + parts[1], parts[2] + parts[3]);
			Nextturn();
			
			if(data[1] == "YOU LOSE" || data[2] == "YOU WIN"){
		        alert(data[1]);
		        return;
		    }
	    }

	};

	socket.onclose = function() {
		console.log("WebSocket connection closed");
	};

	socket.onerror = function(error) {
		console.error("WebSocket Error:", error);
	};
}

var legal;

function parseDataString(dataString) {
    let resultMap = {};
    let regex = /(\w+)=\[(.*?)\]/g;
    let match;
    
    while ((match = regex.exec(dataString)) !== null) {
        let key = match[1];
        let values = match[2].split(', ').map(s => s.trim());
        resultMap[key] = values;
    }
    legal =  resultMap;
    filllegalMove(legal);
}

function sendMove(fromLocation, toLocation) {
	var move = fromLocation + toLocation;
	addMoveToList(move);
	socket.send("move " + move);
}

function addMoveToList(move) {
    var list = document.getElementById("myList");
    var listItem = document.createElement("li");
    listItem.className = "result";
    listItem.textContent = move;

    list.appendChild(listItem);
}

function extractMoves(data) {
	  let moves = data.replace(/[\[\]]/g, '').split(', ');
	  return moves;
}


function RefillMoves(Moves){
	data = extractMoves(Moves);
	for (const move of data) {
		console.log(move);
        handleOpponentMove(move[0] + move[1], move[2] + move[3]);
        addMoveToList(move);
    }
}
