package com.example.backEndService.repository;

import com.example.backEndService.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles,Long> {
    List<UsersRoles> findAllByUserId(Long userId);
}
