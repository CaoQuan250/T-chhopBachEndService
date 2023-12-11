package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.CartItem;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.CartItemRepository;
import com.example.backEndService.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public NoDataBaseResponse save(CartItem cartItem) {
        if (cartItem.getCartId() == null || cartItem.getCartId().equals(0)) {
            throw new ApplicationException("CartId is null");
        }
        if (cartItem.getProductId() == null || cartItem.getProductId().equals(0)) {
            throw new ApplicationException("ProductId is null");
        }
        else {
            cartItemRepository.save(cartItem);
        }
        return new NoDataBaseResponse();
    }

    @Override
    public BaseResponse<List<CartItem>> findAll() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        if (!cartItems.isEmpty()){
            return new BaseResponse<>(cartItems);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    @Override
    public BaseResponse<CartItem> findById(Long id) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if (cartItem.isPresent()){
            return new BaseResponse<>(cartItem.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<List<CartItem>> findCartItemByCartId(Long id) {
        Optional<List<CartItem>> cartItem = cartItemRepository.findCartItemsByCartId(id);
        if (cartItem.isPresent()){
            return new BaseResponse<>(cartItem.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<CartItem> update(Long id, CartItem cart) {
        Optional<CartItem> find = cartItemRepository.findById(id);
        if (find.isPresent()){
            cartItemRepository.save(cart);
            return new BaseResponse<>(cart);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse delete(Long id) {
        Optional<CartItem> find = cartItemRepository.findById(id);
        if (find.isPresent()){
            cartItemRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse deleteAllByCartId(Long cartId) {
        Optional<List<CartItem>> find = cartItemRepository.findCartItemsByCartId(cartId);
        if (find.isPresent()) {
            cartItemRepository.deleteAllByCartId(cartId);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }
}
