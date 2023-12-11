package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.CartItem;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController extends BaseController {

    @Autowired
    CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<NoDataBaseResponse> create(@RequestBody CartItem cartItem) throws ApplicationException {
        return createNoDataResponse(cartItemService.save(cartItem), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<CartItem>>> getList() throws ApplicationException {
        return createResponse(cartItemService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<CartItem>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(cartItemService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<CartItem>> edit(@PathVariable Long id,@RequestBody CartItem cartItem) throws ApplicationException {
        return createResponse(cartItemService.update(id, cartItem));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(cartItemService.delete(id));
    }
}
