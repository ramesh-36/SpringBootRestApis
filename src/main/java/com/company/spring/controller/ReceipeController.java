package com.company.spring.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.spring.entity.Ingredient;
import com.company.spring.entity.Receipe;
import com.company.spring.service.ReciepeService;

@RestController
public class ReceipeController {

	private ReciepeService reciepeService;

	@Autowired
	public ReceipeController(ReciepeService reciepeService) {
		this.reciepeService = reciepeService;
	}

	@GetMapping("/api/receipies")
	public ResponseEntity<List<Receipe>> getReceipies() {
		List<Receipe> receipes = reciepeService.getAllReciepies();
		return new ResponseEntity<>(receipes, HttpStatus.OK);
	}

	@GetMapping("/api/ingredients")
	public ResponseEntity<Set<Ingredient>> getIngredients() {
		Set<Ingredient> list = reciepeService.getAllIngredients();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/api/receipe/{rid}")
	public ResponseEntity<Receipe> getReceipe(@PathVariable("rid") Integer rid) {
		Receipe receipe = reciepeService.getReciepe(rid);
		return new ResponseEntity<>(receipe, HttpStatus.OK);
	}

	@PostMapping("/api/save")
	public ResponseEntity<Receipe> saveReceipe(@RequestBody Receipe receipe) {

		Receipe receipeData = reciepeService.saveReciepe(receipe);

		return new ResponseEntity<>(receipeData, HttpStatus.CREATED);
	}

	@GetMapping("/api/message")
	public ResponseEntity<String> getName() {
		return new ResponseEntity<>("welcome/ramesh", HttpStatus.OK);
	}
}
