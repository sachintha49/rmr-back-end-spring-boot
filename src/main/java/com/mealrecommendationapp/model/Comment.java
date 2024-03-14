package com.mealrecommendationapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comment")
@Getter
@Setter
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String message;
    @JoinColumn(name = "restaurant_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {

    }
}
