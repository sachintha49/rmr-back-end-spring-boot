package com.mealrecommendationapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "RestaurantMenuItemRate")
@Getter
@Setter
public class RestaurantMenuItemRate {

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

    @Column(name = "rate")
    private Float rate;

    @Column(name = "date")
    private Date date;

}
