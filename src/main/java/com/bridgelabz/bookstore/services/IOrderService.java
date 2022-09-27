package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.util.ResponseClass;

import java.util.List;

public interface IOrderService {

    ResponseClass placeOrder(OrderDTO orderDTO, Long cartId, Long addressId, String token);

    ResponseClass cancelOrder(Long orderId, String token);

    List<OrderModel> getAllOrder(String token);

    List<OrderModel> getUserOrders(String token);
}
