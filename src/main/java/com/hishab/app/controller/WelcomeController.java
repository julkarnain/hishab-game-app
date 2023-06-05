package com.hishab.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping(value = "/")
	public ResponseEntity<?> welcome() {
		return new ResponseEntity<>("Welcome to Hishab Game App", HttpStatus.OK);
	}
}