package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.CartItem;
import com.example.backEndService.entities.Category;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.CategoryRepository;
import com.example.backEndService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public NoDataBaseResponse save(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new ApplicationException("Category name is null");
        }
        if (category.getCode() == null || category.getCode().isEmpty()) {
            throw new ApplicationException("Category code is null");
        }
        else {
            categoryRepository.save(category);
        }
        return new NoDataBaseResponse();
    }

    @Override
    public BaseResponse<List<Category>> findAll() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()){
            return new BaseResponse<>(categories);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    @Override
    public BaseResponse<Category> findById(Long id) {
        Optional<Category> categories = categoryRepository.findById(id);
        if (categories.isPresent()){
            return new BaseResponse<>(categories.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<Category> update(Long id, Category category) {
        Optional<Category> find = categoryRepository.findById(id);
        if (find.isPresent()){
            categoryRepository.save(category);
            return new BaseResponse<>(category);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse delete(Long id) {
        Optional<Category> find = categoryRepository.findById(id);
        if (find.isPresent()){
            categoryRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<Category> findCategoriesByName(String name) {
        Optional<Category> categories = categoryRepository.findCategoriesByName(name);
        if (categories.isPresent()){
            return new BaseResponse<>(categories.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }


}
