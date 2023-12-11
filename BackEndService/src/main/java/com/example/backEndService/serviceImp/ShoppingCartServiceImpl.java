package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.ShoppingCart;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.ShoppingCartRepository;
import com.example.backEndService.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public NoDataBaseResponse save(ShoppingCart shoppingCart) {
        if (shoppingCart.getCustomerId() == null) {
            throw new ApplicationException("CustomerId is null");
        }
        else {
            shoppingCartRepository.save(shoppingCart);
        }
        return new NoDataBaseResponse();
    }

    @Override
    public BaseResponse<List<ShoppingCart>> findAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        if (!shoppingCarts.isEmpty()){
            return new BaseResponse<>(shoppingCarts);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    @Override
    public BaseResponse<ShoppingCart> findById(Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
        if (shoppingCart.isPresent()){
            return new BaseResponse<>(shoppingCart.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<ShoppingCart> update(Long id, ShoppingCart shoppingCart) {
        Optional<ShoppingCart> find = shoppingCartRepository.findById(id);
        if (find.isPresent()){
            shoppingCartRepository.save(shoppingCart);
            return new BaseResponse<>(shoppingCart);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse delete(Long id) {
        Optional<ShoppingCart> find = shoppingCartRepository.findById(id);
        if (find.isPresent()){
            shoppingCartRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }
}
