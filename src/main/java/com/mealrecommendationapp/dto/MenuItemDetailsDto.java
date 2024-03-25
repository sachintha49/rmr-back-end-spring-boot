package com.mealrecommendationapp.dto;

import com.mealrecommendationapp.model.MenuItem;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MenuItemDetailsDto {
    private Long menuItemId;
    private String menuItemDescription;
    private String menuItemName;
    private Long menuId;
    private Double largePrice;
    private Double mediumPrice;
    private Double smallPrice;
    private Integer restaurantId;
}
