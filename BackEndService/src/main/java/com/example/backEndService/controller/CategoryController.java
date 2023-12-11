package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Category;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<NoDataBaseResponse> create(@RequestBody Category category) throws ApplicationException {
        return createNoDataResponse(categoryService.save(category), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<Category>>> getList() throws ApplicationException {
        return createResponse(categoryService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<Category>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(categoryService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<Category>> edit(@PathVariable Long id,@RequestBody Category category) throws ApplicationException {
        return createResponse(categoryService.update(id, category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(categoryService.delete(id));
    }
}
