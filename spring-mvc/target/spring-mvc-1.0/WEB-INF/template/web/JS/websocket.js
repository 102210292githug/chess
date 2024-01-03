/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
	var ws = new WebSocket("ws://localhost:8080/spring-mvc/websocket");


    ws.onopen = function(event) {
        console.log("WebSocket connection opened:", event);
    };

    ws.onmessage = function(event) {
        console.log("Message received from server:", event.data);
    };

    ws.onclose = function(event) {
        console.log("WebSocket connection closed:", event);
    };
});