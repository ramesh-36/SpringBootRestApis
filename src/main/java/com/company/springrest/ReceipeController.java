package com.company.springrest;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceipeController {
	@Autowired
	private ReceipeRepository receipeRepository;
	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping("/api/receipies")
	public ResponseEntity<List<Receipe>> getReceipies() {
		List<Receipe> list = new ArrayList<>();
		receipeRepository.findAll().forEach(e -> list.add(e));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/api/ingredients")
	public ResponseEntity<Set<Ingredient>> getIngredients() {
		Set<Ingredient> list = new LinkedHashSet<>();
		ingredientRepository.findAll().forEach(e -> list.add(e));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/api/receipe/{rid}")
	public ResponseEntity<Receipe> getReceipe(@PathVariable("rid") Integer rid) {
		Optional<Receipe> receipe = receipeRepository.findById(rid);
		return new ResponseEntity<>(receipe.get(), HttpStatus.OK);
	}

	@PostMapping("/api/save")
	public ResponseEntity<Receipe> saveReceipe(@RequestBody Receipe receipe) {
		Receipe receipe2 = new Receipe();
		receipe2.setHref(receipe.getHref());
		receipe2.setRid(receipe.getRid());
		receipe2.setThumbnail(receipe.getThumbnail());
		receipe2.setTitle(receipe.getTitle());
		receipe2.setIngredients(receipe.getIngredients());
		Ingredient ingredient = new Ingredient();
		for (Ingredient ingredient2 : receipe.getIngredients()) {

			ingredient.setIid(ingredient2.getIid());
			ingredient.setName(ingredient2.getName());

			ingredientRepository.save(ingredient);
		}

		receipeRepository.save(receipe2);

		return new ResponseEntity<>(receipe2, HttpStatus.CREATED);
	}

	@GetMapping("/api/message")
	public ResponseEntity<String> getName() {
		return new ResponseEntity<>("welcome/ramesh", HttpStatus.OK);
	}
}
