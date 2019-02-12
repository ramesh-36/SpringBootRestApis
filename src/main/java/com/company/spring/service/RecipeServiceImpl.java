package com.company.spring.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.spring.dao.IngredientRepository;
import com.company.spring.dao.ReceipeRepository;
import com.company.spring.entity.Ingredient;
import com.company.spring.entity.Receipe;

@Service
public class RecipeServiceImpl implements ReciepeService {
	private final static Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);
	@Autowired
	private ReceipeRepository receipeRepository;
	@Autowired
	private IngredientRepository ingredientRepository;

	@Transactional(readOnly = true)
	public List<Receipe> getAllReciepies() {
		LOGGER.info("fetching all reciepies");
		List<Receipe> receipes = new ArrayList<>();
		receipeRepository.findAll().forEach(r -> receipes.add(r));
		return receipes;
	}

	@Transactional(readOnly = true)
	public Set<Ingredient> getAllIngredients() {
		LOGGER.info("fetching all Ingredients");
		Set<Ingredient> ingredients = new LinkedHashSet<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		return ingredients;
	}

	@Transactional
	public Receipe saveReciepe(Receipe receipe) {
		Receipe receipe2 = new Receipe();
		receipe2.setHref(receipe.getHref());
		receipe2.setRid(receipe.getRid());
		receipe2.setThumbnail(receipe.getThumbnail());
		receipe2.setTitle(receipe.getTitle());
		receipe2.setIngredients(receipe.getIngredients());

		receipeRepository.save(receipe2);
		saveIngredient(receipe, receipe2);
		LOGGER.info("save reciepie data successfully");
		return receipe2;
	}

	private void saveIngredient(Receipe receipe, Receipe receipe2) {

		Ingredient ingredient = new Ingredient();
		for (Ingredient ingredient2 : receipe.getIngredients()) {

			ingredient.setIid(ingredient2.getIid());
			ingredient.setName(ingredient2.getName());

			ingredientRepository.save(ingredient);
			LOGGER.info("save ingredient data successfully");
		}
	}

	@Transactional(readOnly = true)
	public List<Receipe> getReciepeByIngredients(List<Ingredient> ingredients) {
		List<Receipe> receipies = new ArrayList<>();

		for (Receipe reciepe : getAllReciepies()) {
			for (Ingredient ingredient : ingredients)
				if (reciepe.getIngredients().contains(ingredient)) {
					receipies.add(reciepe);
				}
		}
		LOGGER.info("search  reciepie data by given ingreinets");
		return receipies;
	}

}
