package com.cameraiq.orgapi.service;

import com.cameraiq.orgapi.model.OrganizationUser;
import com.cameraiq.orgapi.model.User;
import com.cameraiq.orgapi.repository.OrganizationUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

public class OrganizationUserServiceTest {

    private OrganizationUserRepository organizationUserRepository;

    private OrganizationUserService organizationUserService;

    @Before
    public void setup() {
        organizationUserRepository = Mockito.mock(OrganizationUserRepository.class);
        organizationUserService = new OrganizationUserService();
        organizationUserService.setOrganizationUserRepository(organizationUserRepository);
    }

    @Test
    public void testGetAllUsersForOrganization() {
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

        Mockito.when(organizationUserRepository.getAllUsersForOrganization(1L)).thenReturn(users);

        List<User> resultingUsers = organizationUserService.getAllUsersForOrganization(1L);

        Assert.assertEquals(2, resultingUsers.size());
        Assert.assertEquals("Jon", resultingUsers.get(0).getFirstName());
        Assert.assertEquals("Jim", resultingUsers.get(1).getFirstName());
        Assert.assertEquals("+19840475848", resultingUsers.get(0).getPhone());
        Assert.assertEquals("+17483958733", resultingUsers.get(1).getPhone());
    }

    @Test
    public void testAddUserToOrganization() {
        OrganizationUser organizationUser = Mockito.mock(OrganizationUser.class);
        organizationUserService.addUserToOrganization(organizationUser);

        Mockito.verify(organizationUserRepository, Mockito.times(1)).addUserToOrganization(any());
    }

    @Test
    public void testDeleteUserFromOrganization() {
        OrganizationUser organizationUser = Mockito.mock(OrganizationUser.class);
        organizationUserService.deleteUserFromOrganization(organizationUser);

        Mockito.verify(organizationUserRepository, Mockito.times(1)).deleteUserFromOrganization(any());
    }

}
