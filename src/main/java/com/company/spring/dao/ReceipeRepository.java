package com.company.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.spring.entity.ReceipeEntity;



@Repository
public interface ReceipeRepository extends CrudRepository<ReceipeEntity, Integer> {

	boolean existsByTitle(String receipeTitle);
	
}
