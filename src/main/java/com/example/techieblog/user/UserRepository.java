package com.example.techieblog.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(String role);

}
