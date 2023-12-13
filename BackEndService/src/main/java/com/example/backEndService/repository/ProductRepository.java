package com.example.backEndService.repository;

import com.example.backEndService.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,
        JpaSpecificationExecutor<Product> {
//    @Query("Select u From Product u Where u.name like '%:name%'")
//    Optional<Product> findByName (@Param("name") String name) ;
}
