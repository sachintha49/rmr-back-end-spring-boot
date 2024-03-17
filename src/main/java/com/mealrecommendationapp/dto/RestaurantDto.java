package com.mealrecommendationapp.dto;

import com.mealrecommendationapp.model.Cuisine;
import com.mealrecommendationapp.model.Facility;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantDto {
    /*this is for User*/
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    /*this is for Restaurant*/
    private String name;
    private String description;
    private String address;
    private String city;
    private String email;
    private Double avgMealRate;
    private String openingHours;
    private String phone;
    private String priceRange;
    private String website;
    private List<Cuisine> cuisines;
    private List<Facility> facilities;
    private Float lat;
    private Float lng;
}
