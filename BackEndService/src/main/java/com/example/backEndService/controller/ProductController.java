package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Product;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController {
    @Autowired
    public ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<NoDataBaseResponse> create(@RequestBody Product product) throws ApplicationException{
        return createNoDataResponse(productService.save(product),HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<Product>>> getList() throws ApplicationException {
        return createResponse(productService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<Product>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(productService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<Product>> edit(@PathVariable Long id,@RequestBody Product product) throws ApplicationException {
        return createResponse(productService.update(id, product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(productService.delete(id));
    }

}
