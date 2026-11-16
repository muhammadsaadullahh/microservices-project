package com.project.user.repositories;

import com.project.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsernameIgnoreCase(final String username);
    boolean existsByUserIdIgnoreCase(final String userId);
}
