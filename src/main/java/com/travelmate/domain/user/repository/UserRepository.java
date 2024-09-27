package com.travelmate.domain.user.repository;

import com.travelmate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);

    Optional<User> findUserByUserEmail(final String userEmail);
    Optional<User> findUserByUserEmailAndPassword(final String userEmail, final String password);

    @Modifying
    @Query("UPDATE User u SET u.delYn = 'Y' WHERE u.userId = :userId")
    void updateDelYnByUserId(@Param("userId") Long userId);
}
