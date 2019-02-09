package com.company.spring.service;

import java.util.List;
import java.util.Set;

import com.company.spring.entity.Ingredient;
import com.company.spring.entity.Receipe;

public interface ReciepeService {
	public List<Receipe> getAllReciepies();

	public Set<Ingredient> getAllIngredients();

	public Receipe getReciepe(Integer rid);

	public Receipe saveReciepe(Receipe receipe);
}
