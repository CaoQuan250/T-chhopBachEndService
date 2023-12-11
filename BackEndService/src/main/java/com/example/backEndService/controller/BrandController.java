package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Brand;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController extends BaseController {
    @Autowired
    BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<NoDataBaseResponse> create(@RequestBody Brand brand) throws ApplicationException {
        return createNoDataResponse(brandService.save(brand), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<Brand>>> getList() throws ApplicationException {
        return createResponse(brandService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<Brand>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(brandService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<Brand>> edit(@PathVariable Long id,@RequestBody Brand brand) throws ApplicationException {
        return createResponse(brandService.update(id, brand));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(brandService.delete(id));
    }
}
