package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Product;
import com.example.backEndService.entities.ProductSpec;

import java.util.List;

public interface ProductSpecService {
    NoDataBaseResponse save(ProductSpec spec);

    BaseResponse<List<ProductSpec>> findAll();

    BaseResponse<ProductSpec> findById(Long id);

    BaseResponse<List<ProductSpec>> findProductSpecByProductId(Long productId);

    BaseResponse<ProductSpec> update(Long id, ProductSpec spec);

    NoDataBaseResponse delete(Long id);
}
