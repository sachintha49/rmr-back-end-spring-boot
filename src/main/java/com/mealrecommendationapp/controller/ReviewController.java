package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.RestaurantMenuItem;
import com.mealrecommendationapp.model.RestaurantMenuItemRate;
import com.mealrecommendationapp.model.RestaurantMenuItemReview;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.service.MenuService;
import com.mealrecommendationapp.service.RestaurantService;
import com.mealrecommendationapp.service.UserService;
import com.mealrecommendationapp.util.SentimentalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/review")
public class ReviewController {

    //@Autowired
  //  private ReviewService reviewService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    // restaurant menu item review
    @PostMapping(value = "/{menuItemId}")
    public RestaurantMenuItemReview saveRestaurantMenuItemReview(@PathVariable("menuItemId") int menuItemId,
                                                                 @RequestBody String review,
                                                                 @RequestParam("username") String username) {
//TODO: This is alternation to get the current user. after applying security we can get it using key
        User user = userService.getUserByUserName(username);
        Optional<RestaurantMenuItem> restaurantMenuItem = menuService.getRestaurantMenuItem(menuItemId);

        RestaurantMenuItemReview restaurantMenuItemReview = new RestaurantMenuItemReview();

        restaurantMenuItemReview.setRate(SentimentalUtil.getSentimentRate(review));
        restaurantMenuItemReview.setDate(new Date());
        restaurantMenuItemReview.setText(review);
        restaurantMenuItemReview.setRestaurantMenuItem(restaurantMenuItem.get());
        restaurantMenuItemReview.setUser(user);

        RestaurantMenuItemReview menuItemReviewDb = menuService.getMenuItemReviewByMenuItemReviewIdAndUserId(menuItemId, user.getId());
        RestaurantMenuItemReview response = null;
        if (menuItemReviewDb != null) {
            menuItemReviewDb.setRate(SentimentalUtil.getSentimentRate(review));
            menuItemReviewDb.setDate(new Date());
            menuItemReviewDb.setText(review);
            response = menuService.saveRestaurantMenuItemReview(menuItemReviewDb);
        } else {
            response = menuService.saveRestaurantMenuItemReview(restaurantMenuItemReview);
        }

        double rateAvg = menuService.getRestaurantMenuItemAverageReview(menuItemId);
        restaurantMenuItem.get().setAverageReview(rateAvg);
        restaurantMenuItem.get().setAverageFinal(menuService.getFinalRating(restaurantMenuItem.get()));
        menuService.saveRestaurantMenuItem(restaurantMenuItem.get());
        restaurantService.updateMenuItemAverage(restaurantMenuItem.get().getRestaurant().getId());
        return response;

    }
}
