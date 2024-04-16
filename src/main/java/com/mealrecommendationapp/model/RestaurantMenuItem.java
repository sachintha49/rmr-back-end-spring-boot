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

    @Column(name = "small_price", columnDefinition = "double default 0.0")
    private Double smallPrice;

    @Column(name = "medium_price", columnDefinition = "double default 0.0")
    private Double mediumPrice;

    @Column(name = "large_price", columnDefinition = "double default 0.0")
    private Double largePrice;

    @Column(name = "avg_rate", columnDefinition = "double default 0.0")
    private Double averageRate;

    @Column(name = "avg_recommend", columnDefinition = "double default 0.0")
    private Double averageRecommend;

    @Column(name = "avg_review", columnDefinition = "double default 0.0")
    private Double averageReview;

    @Column(name = "avg_final", columnDefinition = "double default 0.0")
    private Double averageFinal;

}
