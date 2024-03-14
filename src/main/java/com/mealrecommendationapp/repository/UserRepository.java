package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Uditha on 5/12/2018.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     *
     * @param userName
     * @return
     */
    User findByUserNameAndPassword(String userName, String password);

}
