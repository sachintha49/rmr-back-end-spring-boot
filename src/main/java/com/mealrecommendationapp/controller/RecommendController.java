package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.RestaurantMenuItem;
import com.mealrecommendationapp.model.RestaurantMenuItemRecommend;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.service.LoginService;
import com.mealrecommendationapp.service.MenuService;
import com.mealrecommendationapp.service.RestaurantService;
import com.mealrecommendationapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/{menuItemId}")
    public RestaurantMenuItemRecommend recommendMenuItem(@PathVariable("menuItemId") int menuItemId,
                                                         @RequestBody boolean isRecommend,
                                                         @RequestParam("username") String username) {
        //String authorization = req.getHeader(LoginService.HEADER_AUTHORIZATION);
        //TODO: This is alternation to get the current user. after applying security we can get it using key
        User user = userService.getUserByUserName(username);
        // User user = loginService.getLoggedUser(authorization);

        Optional<RestaurantMenuItem> restaurantMenuItem = menuService.getRestaurantMenuItem(menuItemId);

        RestaurantMenuItemRecommend restaurantMenuItemRecommend = new RestaurantMenuItemRecommend();
        restaurantMenuItemRecommend.setRecommend(isRecommend);
        restaurantMenuItemRecommend.setDate(new Date());
        restaurantMenuItemRecommend.setRestaurantMenuItem(restaurantMenuItem.get());
        restaurantMenuItemRecommend.setUser(user);

        RestaurantMenuItemRecommend menuItemecommendDb = menuService.getMenuItemRecommendByMenuItemRateIdAndUserId(menuItemId,
                user.getId());
        // methana yanne update ekak
        RestaurantMenuItemRecommend response = null;
        if (menuItemecommendDb != null) {
            menuItemecommendDb.setRecommend(isRecommend);
            menuItemecommendDb.setDate(new Date());
            response = menuService.saveRestaurantMenuItemRecommend(menuItemecommendDb);
        } else {
            response = menuService.saveRestaurantMenuItemRecommend(restaurantMenuItemRecommend);
        }

        double recommendAvg = menuService.getAverageRecommendByMenuItemId(menuItemId);

        restaurantMenuItem.get().setAverageRecommend(recommendAvg);
        restaurantMenuItem.get().setAverageFinal(menuService.getFinalRating(restaurantMenuItem.get()));

        menuService.saveRestaurantMenuItem(restaurantMenuItem.get());
        restaurantService.updateMenuItemAverage(restaurantMenuItem.get().getRestaurant().getId());
        return response;
    }

}
