package com.company.springrest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceipeRepository extends CrudRepository<Receipe, Integer> {

}
