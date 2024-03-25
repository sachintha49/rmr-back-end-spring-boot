package com.mealrecommendationapp.dto;

import com.mealrecommendationapp.model.RestaurantMenuItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDto {

    private String category;
    private List<RestaurantMenuItem> restaurantMenuItems = new ArrayList<>();
}
