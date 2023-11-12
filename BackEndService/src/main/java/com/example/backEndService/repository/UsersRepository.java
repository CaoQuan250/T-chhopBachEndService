package com.example.backEndService.repository;

import com.example.backEndService.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String name);
    Optional<Users> findById(Long id);
    boolean existsByUsername(String name);
}
