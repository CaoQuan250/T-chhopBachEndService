package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Product;
import com.example.backEndService.entities.ProductSpec;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-specs")
public class ProductSpecController extends BaseController {

    @Autowired
    ProductSpecService productSpecService;

    @PostMapping("/create")
    public ResponseEntity<NoDataBaseResponse> create(@RequestBody ProductSpec spec) throws ApplicationException {
        return createNoDataResponse(productSpecService.save(spec), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ProductSpec>>> getList() throws ApplicationException {
        return createResponse(productSpecService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<ProductSpec>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(productSpecService.findById(id));
    }

    @GetMapping("/spec-by-product")
    public ResponseEntity<BaseResponse<List<ProductSpec>>> findProductSpecByProductId(@RequestParam Long productId) throws ApplicationException {
        return createResponse(productSpecService.findProductSpecByProductId(productId));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<ProductSpec>> edit(@PathVariable Long id,@RequestBody ProductSpec spec) throws ApplicationException {
        return createResponse(productSpecService.update(id, spec));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(productSpecService.delete(id));
    }
}
