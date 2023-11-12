package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Product;

import java.util.List;

public interface ProductService {

    NoDataBaseResponse save(Product product);

    BaseResponse<List<Product>> findAll();

    BaseResponse<Product> findById(Long id);

    BaseResponse<Product> update(Long id, Product product);

    NoDataBaseResponse delete(Long id);
}
