package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.CartItem;

import java.util.List;

public interface CartItemService {
    NoDataBaseResponse save(CartItem cartItem);

    BaseResponse<List<CartItem>> findAll();

    BaseResponse<CartItem> findById(Long id);

    BaseResponse<List<CartItem>> findCartItemByCartId(Long id);

    NoDataBaseResponse deleteAllByCartId(Long id);

    BaseResponse<CartItem> update(Long id, CartItem cart);

    NoDataBaseResponse delete(Long id);
}
