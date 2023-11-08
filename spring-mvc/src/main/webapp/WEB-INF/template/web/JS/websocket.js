var ws = new WebSocket("ws://localhost:8080/spring-mvc/websocket");
document.addEventListener("DOMContentLoaded", function() {
    ws.onopen = function(event) {
        console.log("WebSocket connection opened:", event);
    };
    ws.onclose = function(event) {
        console.log("WebSocket connection closed:", event);
    };
    document.querySelector('#playOnline').addEventListener('click', () => {
        ws.send('play');
    });
    document.querySelector('#playComputer').addEventListener('click', () => {
        ws.send('playComputer');
    });
});


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

ws.onmessage = function(event) {
    var message = event.data;
    var isW = true;
    console.log(message);

    if (message == "WHITE") {
        KhoiTao();
        WhiteOrBlack(isW);
    } else if (message == "BLACK") {
        KhoiTao();
        WhiteOrBlack(!isW);
    } 
    else if(message == "YOU LOSE"){
        alert(message);
        KhoiTao();
    }
    else {
        var data = message.split(' AND ');
        var parts = data[0];
        parseDataString(data[1]);
	    console.log(legal);
        handleOpponentMove(parts[0] + parts[1], parts[2] + parts[3]);
        Nextturn();
    }
};


function endGame(){
	ws.send("YOU LOSE");
}

function sendMove(fromLocation, toLocation) {
	var move = fromLocation + toLocation;
	ws.send(move);
}
