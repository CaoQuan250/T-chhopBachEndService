package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Category;

import java.util.List;

public interface CategoryService {
    NoDataBaseResponse save(Category category);

    BaseResponse<List<Category>> findAll();

    BaseResponse<Category> findById(Long id);

    BaseResponse<Category> update(Long id, Category category);

    NoDataBaseResponse delete(Long id);
}
