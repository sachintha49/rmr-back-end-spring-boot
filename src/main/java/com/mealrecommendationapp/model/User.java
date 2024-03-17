package com.mealrecommendationapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private String address;

    @Column
    private String email;
}
