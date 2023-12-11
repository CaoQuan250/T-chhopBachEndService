package com.example.backEndService.repository;

import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<List<CartItem>> findCartItemsByCartId (Long id);

    NoDataBaseResponse deleteAllByCartId(Long id);
}
