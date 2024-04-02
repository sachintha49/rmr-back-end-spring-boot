package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.RestaurantMenuItem;
import com.mealrecommendationapp.model.RestaurantMenuItemRate;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.service.LoginService;
import com.mealrecommendationapp.service.MenuService;
import com.mealrecommendationapp.service.RestaurantService;
import com.mealrecommendationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/{menuItemId}")
    public RestaurantMenuItemRate rateMenuItem(@PathVariable("menuItemId") int menuItemId,
                                               @RequestBody float rate,
                                               @RequestParam("username") String username) {
        //String authorization = req.getHeader(LoginService.HEADER_AUTHORIZATION);
        //User user = loginService.getLoggedUser(authorization);

        //TODO: This is alternation to get the current user. after applying security we can get it using key
        User user = userService.getUserByUserName(username);
        Optional<RestaurantMenuItem> restaurantMenuItem = menuService.getRestaurantMenuItem(menuItemId);

        RestaurantMenuItemRate restaurantMenuItemRate = new RestaurantMenuItemRate();

        restaurantMenuItemRate.setRate(rate);
        restaurantMenuItemRate.setDate(new Date());
        restaurantMenuItemRate.setRestaurantMenuItem(restaurantMenuItem.get());
        restaurantMenuItemRate.setUser(user);

        RestaurantMenuItemRate menuItemRateDb = menuService.getMenuItemRateByMenuItemRateIdAndUserId(menuItemId, user.getId());
        RestaurantMenuItemRate response = null;
        if (menuItemRateDb != null) {
            menuItemRateDb.setRate(rate);
            menuItemRateDb.setDate(new Date());
            response = menuService.saveRestaurantMenuItemRate(menuItemRateDb);
        } else {
            response = menuService.saveRestaurantMenuItemRate(restaurantMenuItemRate);
        }
        double rateAvg = menuService.getRestaurantMenuItemAverageRate(menuItemId);
        restaurantMenuItem.get().setAverageRate(rateAvg);
        restaurantMenuItem.get().setAverageFinal(menuService.getFinalRating(restaurantMenuItem.get()));
        menuService.saveRestaurantMenuItem(restaurantMenuItem.get());
        restaurantService.updateMenuItemAverage(restaurantMenuItem.get().getRestaurant().getId());
        return response;
    }

}
