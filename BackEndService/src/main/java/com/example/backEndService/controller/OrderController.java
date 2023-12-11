package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Orders;
import com.example.backEndService.entities.Product;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<NoDataBaseResponse> createOrder(@RequestParam Long customerId) throws ApplicationException{
        return createNoDataResponse(orderService.addOrder(customerId), HttpStatus.CREATED);
    }

    @PostMapping("/update-payment")
    public ResponseEntity<NoDataBaseResponse> updatePayment(@RequestParam Long orderId) throws ApplicationException{
        return createNoDataResponse(orderService.updatePayment(orderId), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<Orders>>> getList() throws ApplicationException {
        return createResponse(orderService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<Orders>> findById(@RequestParam Long id) throws ApplicationException {
        return createResponse(orderService.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BaseResponse<Orders>> edit(@PathVariable Long id,@RequestBody Orders orders) throws ApplicationException {
        return createResponse(orderService.update(id, orders));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoDataBaseResponse> delete(@PathVariable Long id) throws ApplicationException {
        return createNoDataResponse(orderService.delete(id));
    }
}
