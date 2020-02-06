package com.api.orgapi.service;

import com.api.orgapi.model.Organization;
import com.api.orgapi.model.OrganizationUser;
import com.api.orgapi.model.User;
import com.api.orgapi.repository.OrganizationUserRepository;
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

    public void setOrganizationUserRepository(OrganizationUserRepository organizationUserRepository) {
        this.organizationUserRepository = organizationUserRepository;
    }
}
