package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Product;
import com.example.backEndService.entities.ShoppingCart;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController extends BaseController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ResponseEntity<NoDataBaseResponse> create(@RequestBody ShoppingCart cart) throws ApplicationException {
        return createNoDataResponse(shoppingCartService.save(cart), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ShoppingCart>>> getList() throws ApplicationException {
        return createResponse(shoppingCartService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<ShoppingCart>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(shoppingCartService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<ShoppingCart>> edit(@PathVariable Long id,@RequestBody ShoppingCart cart) throws ApplicationException {
        return createResponse(shoppingCartService.update(id, cart));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(shoppingCartService.delete(id));
    }
}
