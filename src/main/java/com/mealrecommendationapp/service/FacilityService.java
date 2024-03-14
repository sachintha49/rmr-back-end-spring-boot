package com.mealrecommendationapp.service;

import com.mealrecommendationapp.model.Facility;
import com.mealrecommendationapp.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;

    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    public List<Facility> getAllFacilities() {
        return (List<Facility>) facilityRepository.findAll();
    }
}
