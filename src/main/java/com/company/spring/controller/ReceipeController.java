package com.company.spring.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.spring.entity.Ingredient;
import com.company.spring.entity.Receipe;
import com.company.spring.exception.ResourceNotFoundException;
import com.company.spring.service.ReciepeService;

@RestController
@RequestMapping("/api")
public class ReceipeController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ReceipeController.class);
	@Autowired
	private ReciepeService reciepeService;

	@GetMapping("/receipies")
	public ResponseEntity<List<Receipe>> getReceipies() throws ResourceNotFoundException {
		try {
			List<Receipe> receipes = reciepeService.getAllReciepies();
			LOGGER.info("fetching all receipies data");

			return new ResponseEntity<>(receipes, HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("resource not found");
		}

	}

	@PostMapping("/receipies")
	public ResponseEntity<List<Receipe>> serachReciepeByIngredients(@RequestBody List<Ingredient> ingredients)
			throws ResourceNotFoundException {
		List<Receipe> receipes = reciepeService.getReciepeByIngredients(ingredients);
		LOGGER.info("fetch all receipies by selected ingredients ");
		return new ResponseEntity<>(receipes,
				receipes != null && !receipes.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/ingredients")
	public ResponseEntity<Set<Ingredient>> getIngredients() {
		Set<Ingredient> list = reciepeService.getAllIngredients();

		LOGGER.info("fetch all ingredients ");
		return new ResponseEntity<>(list, list != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PostMapping("/save")
	public ResponseEntity<Receipe> saveReceipe(@RequestBody Receipe receipe) {
		Receipe receipeData = reciepeService.saveReciepe(receipe);
		if (receipeData == null) {
			throw new ResourceNotFoundException("you are not able to save data");
		}
		LOGGER.info("save receipe data successfully ");

		return new ResponseEntity<>(receipeData, HttpStatus.CREATED);
	}

}
