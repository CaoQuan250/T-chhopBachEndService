package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.CartItem;
import com.example.backEndService.entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    NoDataBaseResponse save(ShoppingCart shoppingCart);

    BaseResponse<List<ShoppingCart>> findAll();

    BaseResponse<ShoppingCart> findById(Long id);

    BaseResponse<ShoppingCart> update(Long id, ShoppingCart shoppingCart);

    NoDataBaseResponse delete(Long id);
}
