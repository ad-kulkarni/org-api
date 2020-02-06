package com.api.orgapi.controller;

import com.api.orgapi.model.Organization;
import com.api.orgapi.model.OrganizationUser;
import com.api.orgapi.model.User;
import com.api.orgapi.service.OrganizationService;
import com.api.orgapi.service.OrganizationUserService;
import com.api.orgapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationUserService organizationUserService;

    @PostMapping("/api/organization/{orgId}/user/{userId}")
    public void addUserToOrganization(@PathVariable(name = "orgId") Long orgId,
                                      @PathVariable(name = "userId") Long userId) {

        organizationUserService.addUserToOrganization(buildOrganizationUserObject(orgId, userId));
    }

    @DeleteMapping("/api/organization/{orgId}/user/{userId}")
    public void deleteUserFromOrganization(@PathVariable(name = "orgId") Long orgId,
                                           @PathVariable(name = "userId") Long userId) {

        organizationUserService.deleteUserFromOrganization(buildOrganizationUserObject(orgId, userId));
    }

    @GetMapping("/api/organization/{orgId}/users")
    public List<User> getAllUsersForOrganization(@PathVariable(name = "orgId") Long orgId) {
        return organizationUserService.getAllUsersForOrganization(orgId);
    }

    @GetMapping("/api/user/{userId}/organizations")
    public List<Organization> getAllOrganizationsForUser(@PathVariable(name = "userId") Long userId) {
        return organizationUserService.getAllOrganizationsForUser(userId);
    }

    private OrganizationUser buildOrganizationUserObject(Long orgId, Long userId) {
        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setOrgId(orgId);
        organizationUser.setUserId(userId);
        return organizationUser;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    public void setOrganizationUserService(OrganizationUserService organizationUserService) {
        this.organizationUserService = organizationUserService;
    }
}
