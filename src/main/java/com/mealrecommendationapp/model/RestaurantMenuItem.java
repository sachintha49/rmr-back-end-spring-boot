package com.mealrecommendationapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RestaurantMenuItem")
@Getter
@Setter
public class RestaurantMenuItem {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(name = "small_price")
    private Double smallPrice;

    @Column(name = "medium_price")
    private Double mediumPrice;

    @Column(name = "large_price")
    private Double largePrice;

    @Column(name = "avg_rate")
    private Double averageRate;

    @Column(name = "avg_recommend")
    private Double averageRecommend;

    @Column(name = "avg_review")
    private Double averageReview;

    @Column(name = "avg_final")
    private Double averageFinal;

}
