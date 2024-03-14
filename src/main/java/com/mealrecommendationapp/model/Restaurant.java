package com.mealrecommendationapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Restaurant")
@Getter
@Setter
public class Restaurant {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String email;
    @Column(name = "avg_meal_rate")
    private Double avgMealRate;
    @Column (name = "opening_hours")
    private String openingHours;
    @Column
    private String phone;
    @Column (name = "price_range")
    private String priceRange;
    @Column
    private String website;
    @ManyToMany
    @JoinTable(name = "RestaurantCuisine", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private List<Cuisine> cuisines;
    @ManyToMany
    @JoinTable(name = "RestaurantFacility", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facility> facilities;
    @Column (name = "lat")
    private Float lat;
    @Column (name = "lng")
    private Float lng;
}
