package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

}
