package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.util.ResponseClass;

public interface IOrderService {
    ResponseClass placeOrder(OrderDTO orderDTO, Long cartId, Long addressId, String token);
}
