package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String username);

    Optional<User> findUserByEmail(String email);
}
