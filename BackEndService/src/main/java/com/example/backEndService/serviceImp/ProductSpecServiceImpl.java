package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.ProductSpec;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.ProductSpecRepository;
import com.example.backEndService.service.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    ProductSpecRepository productSpecRepository;

    @Override
    public NoDataBaseResponse save(ProductSpec spec) {
        if (spec.getProductId() == null) {
            throw new ApplicationException(ERROR.NOT_NULL);
        } else if (spec.getAttribName().isEmpty()) {
            throw new ApplicationException(ERROR.NOT_NULL);
        } else if (spec.getAttribValue().isEmpty()) {
            throw new ApplicationException(ERROR.NOT_NULL);
        } else {
            productSpecRepository.save(spec);
        }
        return new NoDataBaseResponse();
    }

    @Override
    @Cacheable(cacheNames = "product-spec-list", key = "'#key'")
    public BaseResponse<List<ProductSpec>> findAll() {
        List<ProductSpec> specs = productSpecRepository.findAll();
        if (!specs.isEmpty()){
            return new BaseResponse<>(specs);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    @Override
    @Cacheable(cacheNames = "product-spec-detail", key = "'#key'")
    public BaseResponse<ProductSpec> findById(Long id) {
        Optional<ProductSpec> spec = productSpecRepository.findById(id);
        if (spec.isPresent()){
            return new BaseResponse<>(spec.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<List<ProductSpec>> findProductSpecByProductId(Long productId) {
        Optional<List<ProductSpec>> spec = productSpecRepository.findProductSpecByProductId(productId);
        if (spec.isPresent()){
            return new BaseResponse<>(spec.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<ProductSpec> update(Long id, ProductSpec spec) {
        Optional<ProductSpec> find = productSpecRepository.findById(id);
        if (find.isPresent()){
            productSpecRepository.save(spec);
            return new BaseResponse<>(spec);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse delete(Long id) {
        Optional<ProductSpec> find = productSpecRepository.findById(id);
        if (find.isPresent()){
            productSpecRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }
}
