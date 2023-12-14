package com.example.backEndService.repository;

import com.example.backEndService.entities.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSpecRepository extends JpaRepository<ProductSpec, Long> {

    Optional<List<ProductSpec>> findProductSpecByProductId (Long productId);
}
