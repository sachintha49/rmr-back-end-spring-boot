package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.Cuisine;
import com.mealrecommendationapp.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cuisines")
public class CuisineController {
    @Autowired
    private CuisineService cuisineService;

    @PostMapping(value = "/add")
    public Cuisine addCuisine(@RequestBody Cuisine cuisine) {
        return cuisineService.saveCuisine(cuisine);
    }

    @GetMapping
    public ResponseEntity getCuisines() {
        List<Cuisine> cuisines = cuisineService.getAllCuisines();
        return ResponseEntity.ok(cuisines);
    }

}
