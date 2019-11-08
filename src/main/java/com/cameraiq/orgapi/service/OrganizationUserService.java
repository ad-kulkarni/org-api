package com.cameraiq.orgapi.service;

import com.cameraiq.orgapi.model.Organization;
import com.cameraiq.orgapi.model.OrganizationUser;
import com.cameraiq.orgapi.model.User;
import com.cameraiq.orgapi.repository.OrganizationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationUserService {

    @Autowired
    private OrganizationUserRepository organizationUserRepository;

    public List<User> getAllUsersForOrganization(Long orgId) {
        return organizationUserRepository.getAllUsersForOrganization(orgId);
    }

    public List<Organization> getAllOrganizationsForUser(Long userId) {
        return organizationUserRepository.getAllOrganizationsForUser(userId);
    }

    public void addUserToOrganization(OrganizationUser organizationUser) {
        organizationUserRepository.addUserToOrganization(organizationUser);
    }

    public void deleteUserFromOrganization(OrganizationUser organizationUser) {
        organizationUserRepository.deleteUserFromOrganization(organizationUser);
    }
}
