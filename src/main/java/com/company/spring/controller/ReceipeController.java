package com.company.spring.controller;

import java.util.List;

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

import com.company.spring.entity.IngredientsEntity;
import com.company.spring.entity.ReceipeEntity;
import com.company.spring.exception.ResourceNotFoundException;
import com.company.spring.model.Ingredients;
import com.company.spring.model.Receipe;
import com.company.spring.service.ReciepeService;

@RestController
@RequestMapping("/api")
public class ReceipeController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ReceipeController.class);
	@Autowired
	private ReciepeService reciepeService;

	@GetMapping("/receipies")
	public ResponseEntity<List<ReceipeEntity>> getReceipies() throws ResourceNotFoundException {
		try {
			List<ReceipeEntity> receipes = reciepeService.getAllReciepies();
			LOGGER.info("fetching all receipies data");

			return new ResponseEntity<>(receipes, HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("resource not found");
		}

	}

	@PostMapping("/receipies")
	public ResponseEntity<List<ReceipeEntity>> serachReciepeByIngredients(@RequestBody Ingredients ingredients)
			throws ResourceNotFoundException {
		List<ReceipeEntity> receipes = reciepeService.getReciepeByIngredients(ingredients);
		LOGGER.info("fetch all receipies by selected ingredients ");
		return new ResponseEntity<>(receipes,
				receipes != null && !receipes.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/ingredients")
	public ResponseEntity<List<IngredientsEntity>> getIngredients() {
		List<IngredientsEntity> list = reciepeService.getAllIngredients();

		LOGGER.info("fetch all ingredients ");
		return new ResponseEntity<>(list, list != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PostMapping("/save")
	public ResponseEntity<ReceipeEntity> saveReceipe(@RequestBody Receipe receipe) {
		ReceipeEntity receipeData = reciepeService.saveReciepe(receipe);
		if (receipeData == null) {
			throw new ResourceNotFoundException("you are not able to save data");
		}
		LOGGER.info("save receipe data successfully ");

		return new ResponseEntity<>(receipeData, HttpStatus.CREATED);
	}

}
