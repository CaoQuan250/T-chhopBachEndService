package com.example.backEndService.repository;

import com.example.backEndService.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoriesByName(String name);
}
