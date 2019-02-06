package com.company.springrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	@GetMapping("/")
	public ResponseEntity<String> getMessage(){
		return  new ResponseEntity<String>("welcome/ramesh",HttpStatus.OK);
	}

}
