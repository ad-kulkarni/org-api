package com.api.orgapi.controller;

import com.api.orgapi.model.User;
import com.api.orgapi.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @Before
    public void setup() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setFirstName("Jon");
        user1.setLastName("S");
        user1.setEmail("jons67890@mail.com");
        user1.setPhone("+19840475848");

        User user2 = new User();
        user2.setFirstName("Jim");
        user2.setLastName("K");
        user2.setEmail("jimk@mail.com");
        user2.setPhone("+17483958733");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        List<User> resultingUsers = userController.getAllUsers();

        Assert.assertEquals(2, resultingUsers.size());
        Assert.assertEquals("Jon", resultingUsers.get(0).getFirstName());
        Assert.assertEquals("Jim", resultingUsers.get(1).getFirstName());
        Assert.assertEquals("+19840475848", resultingUsers.get(0).getPhone());
        Assert.assertEquals("+17483958733", resultingUsers.get(1).getPhone());
    }

    @Test
    public void testCreateUser() {

        User user = Mockito.mock(User.class);

        userController.createUser(user);

        Mockito.verify(userService, Mockito.times(1)).createUser(user);
    }
}
