package com.cameraiq.orgapi.controller;

import com.cameraiq.orgapi.model.Organization;
import com.cameraiq.orgapi.service.OrganizationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

public class OrganizationControllerTest {

    private OrganizationService organizationService;
    private OrganizationController organizationController;

    @Before
    public void setup() {
        organizationService = Mockito.mock(OrganizationService.class);
        organizationController = new OrganizationController();
        organizationController.setOrganizationService(organizationService);
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

        Mockito.when(organizationService.getAllOrganizations()).thenReturn(organizations);

        List<Organization> resultingOrganizations = organizationController.getAllOrganizations();

        Assert.assertEquals(2, resultingOrganizations.size());
        Assert.assertEquals("org-1", resultingOrganizations.get(0).getName());
        Assert.assertEquals("org-2", resultingOrganizations.get(1).getName());
    }

    @Test
    public void testCreateOrganization() {
        Organization organization = Mockito.mock(Organization.class);

        organizationController.createOrganization(organization);
        Mockito.verify(organizationService, times(1)).createOrganization(organization);
    }
}
