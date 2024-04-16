package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.UserLogin;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {

}
