package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.AI.ServerAI;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

@Controller
public class ServerController {

	@PostMapping("/disconnectServer")
	@ResponseBody
	public ResponseEntity<?> disconnectServer(@RequestParam String serverName) throws IOException {
		System.err.println(disconnectTheServer(serverName));
		if(disconnectTheServer(serverName) == true)
			return ResponseEntity.ok(new ResponseMessage("success", "Server " + serverName + " đã được ngắt kết nối."));
		else 
			return ResponseEntity.ok(new ResponseMessage("erorr", "Không thể ngắt kết nối!"));
	}

	

	private boolean disconnectTheServer(String serverName) throws IOException {
		//return true;
		return ServerAI.disconnectTheServer(serverName);
	}

	static class ResponseMessage {
		private String status;
		private String message;

		public ResponseMessage(String status, String message) {
			this.status = status;
			this.message = message;
		}

		// Getter và Setter
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
