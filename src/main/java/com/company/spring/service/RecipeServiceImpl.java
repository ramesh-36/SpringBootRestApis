package com.company.spring.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.spring.dao.IngredientRepository;
import com.company.spring.dao.ReceipeRepository;
import com.company.spring.entity.Ingredient;
import com.company.spring.entity.Receipe;

@Service
public class RecipeServiceImpl implements ReciepeService {

	private ReceipeRepository receipeRepository;

	private IngredientRepository ingredientRepository;

	@Autowired
	public RecipeServiceImpl(ReceipeRepository receipeRepository, IngredientRepository ingredientRepository) {
		this.receipeRepository = receipeRepository;
		this.ingredientRepository = ingredientRepository;
	}

	@Transactional(readOnly = true)
	public List<Receipe> getAllReciepies() {
		List<Receipe> receipes = new ArrayList<>();
		receipeRepository.findAll().forEach(r -> receipes.add(r));
		return receipes;
	}

	@Transactional(readOnly = true)
	public Set<Ingredient> getAllIngredients() {
		Set<Ingredient> ingredients = new LinkedHashSet<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		return ingredients;
	}

	@Transactional(readOnly = true)
	public Receipe getReciepe(Integer rid) {
		Optional<Receipe> receipe = receipeRepository.findById(rid);
		return receipe.get();
	}

	@Transactional
	public Receipe saveReciepe(Receipe receipe) {
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
		return receipe2;
	}

}
