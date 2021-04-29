package com.alphacoder.springsecurityoauth2.repository;

import com.alphacoder.springsecurityoauth2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
