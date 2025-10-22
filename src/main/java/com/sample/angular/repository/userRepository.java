package com.sample.angular.repository;

import com.sample.angular.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<user,Long> {
    Optional<user> findByUsername(String username);
}