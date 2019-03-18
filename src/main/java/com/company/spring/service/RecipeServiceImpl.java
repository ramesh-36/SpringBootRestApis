package com.company.spring.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.spring.dao.IngredientRepository;
import com.company.spring.dao.ReceipeRepository;
import com.company.spring.entity.IngredientsEntity;
import com.company.spring.entity.ReceipeEntity;
import com.company.spring.model.Ingredients;
import com.company.spring.model.Receipe;

@Service
public class RecipeServiceImpl implements ReciepeService {
	private final static Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);
	@Autowired
	private ReceipeRepository receipeRepository;
	@Autowired
	private IngredientRepository ingredientRepository;

	@Transactional(readOnly = true)
	public List<ReceipeEntity> getAllReciepies() {
		LOGGER.info("fetching all reciepies");
		List<ReceipeEntity> receipes = new ArrayList<>();
		receipeRepository.findAll().forEach(r -> receipes.add(r));
		return receipes;
	}

	@Transactional(readOnly = true)
	public List<IngredientsEntity> getAllIngredients() {
		LOGGER.info("fetching all Ingredients");
		List<IngredientsEntity> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		return ingredients;
	}

	@Transactional
	public ReceipeEntity saveReciepe(Receipe receipe) {
		ReceipeEntity receipe2 = new ReceipeEntity();
		receipe2.setHref(receipe.getHref());
		receipe2.setThumbnail(receipe.getThumbnail());
		receipe2.setTitle(receipe.getTitle());
		receipe2.setIngredients(Arrays.asList(receipe.getIngredients()));
		IngredientsEntity ingredientsEntity = new IngredientsEntity();
		for (String ingredientName : receipe2.getIngredients()) {
			ingredientsEntity = new IngredientsEntity();
			ingredientsEntity.setIngredient(ingredientName);

			receipe2.getIngredientsCollection().add(ingredientsEntity);
		}

		if (!existsByTitle(receipe.getTitle())) {
			receipe2.setStatus(true);
			// receipeMOList.add(receipeMO);
			receipeRepository.save(receipe2);
		} else {
			return receipe2;
		}

		LOGGER.info("save reciepie data successfully");
		return receipe2;
	}

	public boolean existsByTitle(String receipeTitle) {
		LOGGER.info("Find By Title ::: {}", receipeTitle);

		return receipeRepository.existsByTitle(receipeTitle);

	}

	@Transactional(readOnly = true)
	public List<ReceipeEntity> getReciepeByIngredients(Ingredients ingredient) {
		List<ReceipeEntity> receipeMOList = new ArrayList<ReceipeEntity>();

		for (String ingredientName : ingredient.getIngredients()) {
			LOGGER.debug("Ingredient Name {}", ingredientName);

			List<IngredientsEntity> ingredientsMO = ingredientRepository.findByIngredient(ingredientName);
			for (IngredientsEntity ingredient1 : ingredientsMO) {
				receipeMOList.add(ingredient1.getReceipesCollection().get(0));
			}
		}

		LOGGER.debug("Retrieve Receipes by Ingredients return object:: {}", receipeMOList);

		return receipeMOList;
	}

}
