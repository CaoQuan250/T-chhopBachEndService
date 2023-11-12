package com.example.backEndService.repository;

import com.example.backEndService.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(String name);

    List<Roles> findAllByIdIn(List<Long> id);

    boolean existsByName(String name);
}
