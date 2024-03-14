package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.Facility;
import com.mealrecommendationapp.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/facilities")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @PostMapping(value = "/add")
    public Facility addFacility(@RequestBody Facility facility) {
        return facilityService.saveFacility(facility);
    }

    @GetMapping
    public ResponseEntity getFacilities() {
        List<Facility> cuisines = facilityService.getAllFacilities();
        return ResponseEntity.ok(cuisines);
    }
}
