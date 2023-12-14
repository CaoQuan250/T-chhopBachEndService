package com.example.backEndService.serviceImp;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.common.Constants;
import com.example.backEndService.entities.CartItem;
import com.example.backEndService.entities.Orders;
import com.example.backEndService.entities.Product;
import com.example.backEndService.entities.ShoppingCart;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.CartItemRepository;
import com.example.backEndService.repository.OrderRepository;
import com.example.backEndService.repository.ProductRepository;
import com.example.backEndService.repository.ShoppingCartRepository;
import com.example.backEndService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    public NoDataBaseResponse addOrder(Long customerId) {

        Optional<ShoppingCart> findCart = shoppingCartRepository.findByCustomerId(customerId);
        if (findCart.isEmpty()) {
            throw new ApplicationException("Cannot find cart with this customerId: " + customerId);
        }

        List<CartItem> listCartItem = new ArrayList<>();

        Optional<List<CartItem>> findCartItem = cartItemRepository.findCartItemsByCartId(findCart.get().getId());
        if (findCartItem.isEmpty()) {
            throw new ApplicationException("Cannot find cart item with this cartId: " + findCart.get().getId());
        }

        listCartItem = findCartItem.get();

        Orders orders = new Orders();

        for (CartItem cart: listCartItem
             ) {

            orders.setCustomerId(customerId);
            orders.setProductId(cart.getProductId());
            orders.setQuantity(cart.getQuantity());

            //get price
            Optional<Product> findProduct = productRepository.findById(cart.getProductId());
            if (findProduct.isEmpty()){
                throw new ApplicationException("Cannot find product with this productId: " + cart.getProductId());
            }

            orders.setPrice(findProduct.get().getPrice());
            orders.setStatus(Constants.ORDER_CODE.PAYMENT_PENDING);

            //persist to DB
            orderRepository.save(orders);
        }
        //delete cart item after save to order

        cartItemRepository.deleteAllByCartId(findCart.get().getId());
        return new NoDataBaseResponse();
    }

    public NoDataBaseResponse updatePayment(Long orderId) {
        Optional<Orders> findOrder = orderRepository.findById(orderId);
        if (findOrder.isEmpty()) {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }

        Orders orders = findOrder.get();

        orders.setPayDate(new Date());
        orders.setStatus(Constants.ORDER_CODE.PAYMENT_SUCCESSFUL);

        try {
            update(orderId, orders);
        } catch (Exception exception) {
            throw new ApplicationException("Error updating payment status: " + exception.getMessage());
        }

        return new NoDataBaseResponse();
    }

    @Override
    public NoDataBaseResponse save(Orders orders) {
        if (orders.getCustomerId() == null || orders.getCustomerId().equals(0)) {
            throw new ApplicationException("CustomerId is null");
        }
        if (orders.getProductId() == null || orders.getProductId().equals(0)) {
            throw new ApplicationException("ProductId is null");
        }
        else {
            orderRepository.save(orders);
        }
        return new NoDataBaseResponse();
    }

    @Override
    @Cacheable(cacheNames = "order-list", key = "'#key'")
    public BaseResponse<List<Orders>> findAll() {
        List<Orders> orders = orderRepository.findAll();
        if (!orders.isEmpty()){
            return new BaseResponse<>(orders);
        } else {
            throw new ApplicationException(ERROR.NO_DATA);
        }
    }

    @Override
    @Cacheable(cacheNames = "order-list", key = "'#key'")
    public BaseResponse<Orders> findById(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        if (order.isPresent()){
            return new BaseResponse<>(order.get());
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public BaseResponse<Orders> update(Long id, Orders orders) {
        Optional<Orders> find = orderRepository.findById(id);
        if (find.isPresent()){
            orderRepository.save(orders);
            return new BaseResponse<>(orders);
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }

    @Override
    public NoDataBaseResponse delete(Long id) {
        Optional<Orders> find = orderRepository.findById(id);
        if (find.isPresent()){
            orderRepository.deleteById(id);
            return new NoDataBaseResponse();
        } else {
            throw new ApplicationException(ERROR.NO_DATA_FOUND);
        }
    }
}
