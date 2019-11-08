package com.cameraiq.orgapi.repository;

import com.cameraiq.orgapi.model.Organization;
import com.cameraiq.orgapi.model.OrganizationUser;
import com.cameraiq.orgapi.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

public class OrganizationUserRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    private UserRepository userRepository;
    private OrganizationRepository organizationRepository;
    private OrganizationUserRepository organizationUserRepository;

    @Before
    public void setup() {
        jdbcTemplate = Mockito.mock(JdbcTemplate.class);

        userRepository = Mockito.mock(UserRepository.class);
        organizationRepository = Mockito.mock(OrganizationRepository.class);
        organizationUserRepository = new OrganizationUserRepository();
        organizationUserRepository.setJdbcTemplate(jdbcTemplate);
        organizationUserRepository.setUserRepository(userRepository);
        organizationUserRepository.setOrganizationRepository(organizationRepository);
    }

    @Test
    public void testGetAllOrganizationsForUser() {
        OrganizationUser organizationUser1 = new OrganizationUser();
        organizationUser1.setOrgId(1L);
        organizationUser1.setUserId(2L);

        OrganizationUser organizationUser2 = new OrganizationUser();
        organizationUser2.setOrgId(2L);
        organizationUser2.setUserId(1L);

        List<OrganizationUser> organizationUsers = new ArrayList<>();
        organizationUsers.add(organizationUser1);
        organizationUsers.add(organizationUser2);

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

        Mockito.when(jdbcTemplate.query(anyString(), (ResultSetExtractor<Object>) any(), any())).thenReturn(organizationUsers);

        Mockito.when(organizationRepository.findAllById(any())).thenReturn(organizations);

        List<Organization> resultingOrganizations = organizationUserRepository.getAllOrganizationsForUser(1L);

        Assert.assertEquals(2, resultingOrganizations.size());
        Assert.assertEquals("org-1", resultingOrganizations.get(0).getName());
        Assert.assertEquals("org-2", resultingOrganizations.get(1).getName());
    }

    @Test
    public void testGetAllUsersForOrganization() {
        OrganizationUser organizationUser1 = new OrganizationUser();
        organizationUser1.setOrgId(1L);
        organizationUser1.setUserId(2L);

        OrganizationUser organizationUser2 = new OrganizationUser();
        organizationUser2.setOrgId(2L);
        organizationUser2.setUserId(1L);

        List<OrganizationUser> organizationUsers = new ArrayList<>();
        organizationUsers.add(organizationUser1);
        organizationUsers.add(organizationUser2);

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

        Mockito.when(jdbcTemplate.query(anyString(), (ResultSetExtractor<Object>) any(), any())).thenReturn(organizationUsers);

        Mockito.when(userRepository.findAllById(any())).thenReturn(users);

        List<User> resultingUsers = organizationUserRepository.getAllUsersForOrganization(1L);

        Assert.assertEquals(2, resultingUsers.size());
        Assert.assertEquals("Jon", resultingUsers.get(0).getFirstName());
        Assert.assertEquals("Jim", resultingUsers.get(1).getFirstName());
        Assert.assertEquals("+19840475848", resultingUsers.get(0).getPhone());
        Assert.assertEquals("+17483958733", resultingUsers.get(1).getPhone());
    }

    @Test(expected = ResponseStatusException.class)
    public void testAddUserToOrganizationWhenOrganizationIsMissing() {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(1L);
        organizationUser.setUserId(2L);

        Optional<Organization> optional = Optional.ofNullable(null);

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optional);

        organizationUserRepository.addUserToOrganization(organizationUser);
    }

    @Test(expected = ResponseStatusException.class)
    public void testAddUserToOrganizationWhenUserIsMissing() {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(1L);
        organizationUser.setUserId(2L);

        Optional<Organization> optionalOrganization = Optional.ofNullable(new Organization());

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optionalOrganization);

        Optional<User> optionalUser = Optional.ofNullable(null);

        Mockito.when(userRepository.findById(2L)).thenReturn(optionalUser);

        organizationUserRepository.addUserToOrganization(organizationUser);
    }

    @Test
    public void testAddValidUserToValidOrganization() {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(1L);
        organizationUser.setUserId(2L);

        Optional<Organization> optionalOrganization = Optional.ofNullable(new Organization());

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optionalOrganization);

        Optional<User> optionalUser = Optional.ofNullable(new User());

        Mockito.when(userRepository.findById(2L)).thenReturn(optionalUser);

        organizationUserRepository.addUserToOrganization(organizationUser);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(anyString(), anyLong(), anyLong());
    }

    @Test(expected = ResponseStatusException.class)
    public void testDeleteUserFromOrganizationWhenOrganizationIsMissing() {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(1L);
        organizationUser.setUserId(2L);

        Optional<Organization> optional = Optional.ofNullable(null);

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optional);

        organizationUserRepository.deleteUserFromOrganization(organizationUser);
    }

    @Test(expected = ResponseStatusException.class)
    public void testDeleteUserFromOrganizationWhenUserIsMissing() {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(1L);
        organizationUser.setUserId(2L);

        Optional<Organization> optionalOrganization = Optional.ofNullable(new Organization());

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optionalOrganization);

        Optional<User> optionalUser = Optional.ofNullable(null);

        Mockito.when(userRepository.findById(2L)).thenReturn(optionalUser);

        organizationUserRepository.deleteUserFromOrganization(organizationUser);
    }

    @Test
    public void testDeleteValidUserFromValidOrganizationWhenUserIsMissing() {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(1L);
        organizationUser.setUserId(2L);

        Optional<Organization> optionalOrganization = Optional.ofNullable(new Organization());

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optionalOrganization);

        Optional<User> optionalUser = Optional.ofNullable(new User());

        Mockito.when(userRepository.findById(2L)).thenReturn(optionalUser);

        organizationUserRepository.addUserToOrganization(organizationUser);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(anyString(), anyLong(), anyLong());
    }
}
