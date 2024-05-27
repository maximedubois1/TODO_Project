package com.sp.repository;

import com.sp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findBySurname(String surname);

    @Query("SELECT u FROM UserEntity u WHERE u.surname = ?1")
    UserEntity findByUsername(String username);
}
