package com.company.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.spring.entity.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}
