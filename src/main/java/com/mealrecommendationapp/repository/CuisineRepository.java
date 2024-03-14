package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.Cuisine;
import org.springframework.data.repository.CrudRepository;

public interface CuisineRepository extends CrudRepository<Cuisine, Integer> {
}
