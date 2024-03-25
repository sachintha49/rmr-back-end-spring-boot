package com.mealrecommendationapp.dto;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String username;
    private String restaurantId;
    private String role;
}
