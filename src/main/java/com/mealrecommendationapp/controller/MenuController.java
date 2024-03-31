package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.dto.MenuItemDto;
import com.mealrecommendationapp.model.MenuItem;
import com.mealrecommendationapp.model.RestaurantMenuItem;
import com.mealrecommendationapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    MenuService menuService;
    @GetMapping(value = "/items")
    public ResponseEntity getMenuItems() {
        List<MenuItem> menuItems = menuService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    /* add menu item to the particular restaurant*/
    @PostMapping(value = "/{restaurantId}")
    public ResponseEntity addMenuItem(@PathVariable("restaurantId") int restaurantId, @RequestBody MenuItemDto menuItemDto) {
        RestaurantMenuItem restaurantMenuItem = menuService.saveMenuItem(restaurantId, menuItemDto);
        return ResponseEntity.ok(restaurantMenuItem);
    }

    @GetMapping(value = "/{restaurantId}")
    public List<RestaurantMenuItem> getRestaurantMenuItems(@PathVariable("restaurantId") int restaurantId) {
        List<RestaurantMenuItem> restaurantMenuItems = menuService.getRestaurantMenuItems(restaurantId);
        return restaurantMenuItems;
    }

    @GetMapping(value = "/item/{menuItemId}")
    public Optional<RestaurantMenuItem> getRestaurantMenuItem(@PathVariable("menuItemId") int menuItemId) {
        return menuService.getRestaurantMenuItem(menuItemId);
    }
}
