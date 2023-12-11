package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Brand;
import com.example.backEndService.entities.Category;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.BrandRepository;
import com.example.backEndService.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public NoDataBaseResponse save(Brand brand) {
        if (brand.getName() == null || brand.getName().isEmpty()) {
            throw new ApplicationException("Brand name is null");
        }
        if (brand.getCode() == null || brand.getCode().isEmpty()) {
            throw new ApplicationException("Category code is null");
        }
        else {
            brandRepository.save(brand);
        }
        return new NoDataBaseResponse();
    }

    @Override
    public BaseResponse<List<Brand>> findAll() {
        List<Brand> brands = brandRepository.findAll();
        if (!brands.isEmpty()){
            return new BaseResponse<>(brands);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    @Override
    public BaseResponse<Brand> findById(Long id) {
        Optional<Brand> categories = brandRepository.findById(id);
        if (categories.isPresent()){
            return new BaseResponse<>(categories.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<Brand> update(Long id, Brand brand) {
        Optional<Brand> find = brandRepository.findById(id);
        if (find.isPresent()){
            brandRepository.save(brand);
            return new BaseResponse<>(brand);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse delete(Long id) {
        Optional<Brand> find = brandRepository.findById(id);
        if (find.isPresent()){
            brandRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }
}
