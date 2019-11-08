package com.cameraiq.orgapi.repository;

import com.cameraiq.orgapi.model.Organization;
import com.cameraiq.orgapi.model.OrganizationUser;
import com.cameraiq.orgapi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationUserRepository {

    private Logger logger = LoggerFactory.getLogger(OrganizationUserRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizationsForUser(Long userId) {
        List<OrganizationUser> orgUsers = jdbcTemplate.query("select * from organization_user where user_id = ?", new Object[] {userId}, new BeanPropertyRowMapper<>(OrganizationUser.class));

        List<Long> orgIds = new ArrayList<>();
        for(OrganizationUser orgUser : orgUsers) {
            orgIds.add(orgUser.getOrgId());
        }

        return organizationRepository.findAllById(orgIds);
    }

    public List<User> getAllUsersForOrganization(Long orgId) {
        List<OrganizationUser> orgUsers = jdbcTemplate.query("select * from organization_user where org_id = ?", new Object[] {orgId}, new BeanPropertyRowMapper<>(OrganizationUser.class));

        List<Long> userIds = new ArrayList<>();
        for(OrganizationUser orgUser : orgUsers) {
            userIds.add(orgUser.getUserId());
        }

        return userRepository.findAllById(userIds);
    }

    public void addUserToOrganization(OrganizationUser organizationUser) {
        validateOrganizationAndUser(organizationUser.getOrgId(), organizationUser.getUserId());

        jdbcTemplate.update("insert into organization_user (org_id, user_id) values (?, ?)", new Object[] {organizationUser.getOrgId(), organizationUser.getUserId()});
    }

    public void deleteUserFromOrganization(OrganizationUser organizationUser) {
        validateOrganizationAndUser(organizationUser.getOrgId(), organizationUser.getUserId());

        jdbcTemplate.update("delete from organization_user where org_id = ? and user_id = ?", new Object[] {organizationUser.getOrgId(), organizationUser.getUserId()});
    }

    private void validateOrganizationAndUser(Long orgId, Long userId) {
        if(!organizationRepository.findById(orgId).isPresent()) {
            logger.error("Organization does not exist!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }

        if(!userRepository.findById(userId).isPresent()) {
            logger.error("User does not exist!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
}
