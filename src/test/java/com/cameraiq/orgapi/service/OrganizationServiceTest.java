package com.cameraiq.orgapi.service;

import com.cameraiq.orgapi.model.Organization;
import com.cameraiq.orgapi.repository.OrganizationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationServiceTest {

    private OrganizationRepository organizationRepository;
    private OrganizationService organizationService;

    @Before
    public void setup() {
        organizationRepository = Mockito.mock(OrganizationRepository.class);
        organizationService = new OrganizationService();
        organizationService.setOrganizationRepository(organizationRepository);
    }

    @Test
    public void testCreateOrganization() {
        Organization organization = Mockito.mock(Organization.class);
        organizationService.createOrganization(organization);

        Mockito.verify(organizationRepository, Mockito.times(1)).save(organization);
    }

    @Test
    public void testGetAllOrganizations() {
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

        Mockito.when(organizationRepository.findAll()).thenReturn(organizations);

        List<Organization> resultingOrganizations = organizationService.getAllOrganizations();

        Assert.assertEquals(2, resultingOrganizations.size());
        Assert.assertEquals("org-1", resultingOrganizations.get(0).getName());
        Assert.assertEquals("org-2", resultingOrganizations.get(1).getName());
    }

    @Test
    public void testGetOrganizationById() {

        Optional<Organization> optional = Optional.ofNullable(null);

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optional);

        Optional<Organization> resultingOptional = organizationService.getOrganizationById(1L);
        Assert.assertFalse(resultingOptional.isPresent());

        Organization organization = Mockito.mock(Organization.class);

        optional = Optional.ofNullable(organization);

        Mockito.when(organizationRepository.findById(1L)).thenReturn(optional);

        resultingOptional = organizationService.getOrganizationById(1L);
        Assert.assertTrue(resultingOptional.isPresent());
    }
}
