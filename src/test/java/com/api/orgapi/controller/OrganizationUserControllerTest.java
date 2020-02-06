package com.api.orgapi.controller;

import com.api.orgapi.model.Organization;
import com.api.orgapi.model.User;
import com.api.orgapi.service.OrganizationService;
import com.api.orgapi.service.OrganizationUserService;
import com.api.orgapi.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

public class OrganizationUserControllerTest {

    private UserService userService;
    private OrganizationService organizationService;
    private OrganizationUserService organizationUserService;

    private OrganizationUserController organizationUserController;

    @Before
    public void setup() {
        userService = Mockito.mock(UserService.class);
        organizationService = Mockito.mock(OrganizationService.class);
        organizationUserService = Mockito.mock(OrganizationUserService.class);

        organizationUserController = new OrganizationUserController();
        organizationUserController.setUserService(userService);
        organizationUserController.setOrganizationService(organizationService);
        organizationUserController.setOrganizationUserService(organizationUserService);
    }

    @Test
    public void testAddUserToOrganization() {
        organizationUserController.addUserToOrganization(1L, 2L);

        Mockito.verify(organizationUserService, Mockito.times(1)).addUserToOrganization(any());
    }

    @Test
    public void testDeleteUserFromOrganization() {
        organizationUserController.deleteUserFromOrganization(1L, 2L);

        Mockito.verify(organizationUserService, Mockito.times(1)).deleteUserFromOrganization(any());
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

        Mockito.when(organizationUserService.getAllUsersForOrganization(1L)).thenReturn(users);

        List<User> resultingUsers = organizationUserController.getAllUsersForOrganization(1L);

        Assert.assertEquals(2, resultingUsers.size());
        Assert.assertEquals("Jon", resultingUsers.get(0).getFirstName());
        Assert.assertEquals("Jim", resultingUsers.get(1).getFirstName());
        Assert.assertEquals("+19840475848", resultingUsers.get(0).getPhone());
        Assert.assertEquals("+17483958733", resultingUsers.get(1).getPhone());
    }

    @Test
    public void testGetAllOrganizationsForUser() {
        Organization organization1 = new Organization();
        organization1.setName("org-1");
        organization1.setAddress("123 S J Street");
        organization1.setPhone("+1234567890");

        Organization organization2 = new Organization();
        organization2.setName("org-2");
        organization2.setAddress("123 N J Street");
        organization2.setPhone("+19876543210");

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);

        Mockito.when(organizationUserService.getAllOrganizationsForUser(1L)).thenReturn(organizations);

        List<Organization> resultingOrganizations = organizationUserController.getAllOrganizationsForUser(1L);

        Assert.assertEquals(2, resultingOrganizations.size());
        Assert.assertEquals("org-1", resultingOrganizations.get(0).getName());
        Assert.assertEquals("org-2", resultingOrganizations.get(1).getName());
    }
}
