package com.mealrecommendationapp.dto;

import lombok.Data;

@Data
public class MenuItemDto {
    private Double smallPrice;
    private Double mediumPrice;
    private Double largePrice;
    private Integer menuItemId;
    private Integer restaurantId;
    private Integer menuId;
}
