package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.Facility;
import org.springframework.data.repository.CrudRepository;

public interface FacilityRepository extends CrudRepository<Facility, Integer> {
}
