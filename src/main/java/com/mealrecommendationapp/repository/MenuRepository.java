package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

}
