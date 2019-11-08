package com.cameraiq.orgapi.service;

import com.cameraiq.orgapi.model.User;
import com.cameraiq.orgapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    @Test
    public void testCreateUser() {
        User user = Mockito.mock(User.class);
        userService.createUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
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

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> resultingUsers = userService.getAllUsers();

        Assert.assertEquals(2, resultingUsers.size());
        Assert.assertEquals("Jon", resultingUsers.get(0).getFirstName());
        Assert.assertEquals("Jim", resultingUsers.get(1).getFirstName());
        Assert.assertEquals("+19840475848", resultingUsers.get(0).getPhone());
        Assert.assertEquals("+17483958733", resultingUsers.get(1).getPhone());
    }

    @Test
    public void testGetUserById() {

        Optional<User> optional = Optional.ofNullable(null);

        Mockito.when(userRepository.findById(1L)).thenReturn(optional);

        Optional<User> resultingOptional = userService.getUserById(1L);
        Assert.assertFalse(resultingOptional.isPresent());

        User user = Mockito.mock(User.class);

        optional = Optional.ofNullable(user);

        Mockito.when(userRepository.findById(1L)).thenReturn(optional);

        resultingOptional = userService.getUserById(1L);
        Assert.assertTrue(resultingOptional.isPresent());
    }
}
