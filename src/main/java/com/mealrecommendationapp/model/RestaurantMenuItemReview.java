package com.mealrecommendationapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "RestaurantMenuItemReview")
@Getter
@Setter
public class RestaurantMenuItemReview {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_item_id")
    private RestaurantMenuItem restaurantMenuItem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "date")
    private Date date;


}
