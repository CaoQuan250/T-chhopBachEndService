package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Brand;
import com.example.backEndService.entities.CartItem;

import java.util.List;

public interface BrandService {
    NoDataBaseResponse save(Brand brand);

    BaseResponse<List<Brand>> findAll();

    BaseResponse<Brand> findById(Long id);

    BaseResponse<Brand> update(Long id, Brand brand);

    NoDataBaseResponse delete(Long id);
}
