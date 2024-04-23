package com.mealrecommendationapp.service;

import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUserName("sachi");
        user.setAddress("Kottawa");
        user.setEmail("sachi@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User userObj = userService.saveUser(new User());
        assertEquals(user.getUserName(), userObj.getUserName());
    }

  /*  @Test
    public void getUserById() {
        User user = new User();
        user.setId(1);
        user.setUserName("sachi");
        user.setAddress("Kottawa");
        user.setEmail("sachi@gmail.com");
        when(userRepository.findById(any(Integer.class))).thenReturn(user);

        Optional<User> userObj = userService.getUserById(1);
        assertEquals(user.getId(), userObj.get().getId());
    }*/
}