package com.repositories;

import com.domain.User;
import com.shared.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> getUserById(Long id);

    @Query("select u from User u where u.login = :login and u.password = :password")
    public User findUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
