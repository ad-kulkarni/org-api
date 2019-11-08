package com.cameraiq.orgapi.controller;

import com.cameraiq.orgapi.model.Organization;
import com.cameraiq.orgapi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/api/organizations")
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping("/api/organization")
    public void createOrganization(Organization organization) {
        organizationService.createOrganization(organization);
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
}
