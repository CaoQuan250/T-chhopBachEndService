package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Product;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.ProductRepository;
import com.example.backEndService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public NoDataBaseResponse save(Product product){

        if (product.getName().isBlank()) {
            throw new ApplicationException(ERROR.NOT_NULL);
        } else {
            productRepository.save(product);
        }
        return new NoDataBaseResponse();
    }

    public BaseResponse<List<Product>> findAll() throws ApplicationException{
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()){
            return new BaseResponse<>(products);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    public BaseResponse<Product> findById(Long id) throws ApplicationException{
        Optional<Product> employee = productRepository.findById(id);
        if (employee.isPresent()){
            return new BaseResponse<>(employee.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    public BaseResponse<Product> update(Long id, Product product) throws ApplicationException{
        Optional<Product> find = productRepository.findById(id);
        if (find.isPresent()){
            productRepository.save(product);
            return new BaseResponse<>(product);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    public NoDataBaseResponse delete(Long id){
        Optional<Product> find = productRepository.findById(id);
        if (find.isPresent()){
            productRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }
}
