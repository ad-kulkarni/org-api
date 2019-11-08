package com.cameraiq.orgapi.repository;

import com.cameraiq.orgapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
