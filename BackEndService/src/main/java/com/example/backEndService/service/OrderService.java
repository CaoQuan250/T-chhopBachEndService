package com.example.backEndService.service;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.entities.Orders;

import java.util.List;

public interface OrderService {
    NoDataBaseResponse save(Orders orders);

    BaseResponse<List<Orders>> findAll();

    BaseResponse<Orders> findById(Long id);

    BaseResponse<Orders> update(Long id, Orders orders);

    NoDataBaseResponse delete(Long id);

    NoDataBaseResponse addOrder(Long customerId);

    NoDataBaseResponse updatePayment(Long orderId);
}
