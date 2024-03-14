package com.mealrecommendationapp.service;

import com.mealrecommendationapp.model.Cuisine;
import com.mealrecommendationapp.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineService {
    @Autowired
    private CuisineRepository cuisineRepository;
    public Cuisine saveCuisine(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public List<Cuisine> getAllCuisines() {
        return (List<Cuisine>) cuisineRepository.findAll();
    }
}
