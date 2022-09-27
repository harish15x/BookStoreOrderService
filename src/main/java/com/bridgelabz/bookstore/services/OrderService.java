package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.exception.OrderNotFoundException;
import com.bridgelabz.bookstore.model.AddressModel;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.repository.Addressrepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.util.ResponseClass;
import com.bridgelabz.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    Addressrepository addressrepository;

    @Override
    public ResponseClass placeOrder(OrderDTO orderDTO, Long cartId, Long addressId, String token) {
        boolean isCartPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isCartPresent) {
            Optional<OrderModel> isCartAvailable = orderRepository.findById(cartId);
            if (isCartAvailable.isPresent()){
                if (isCartAvailable.isPresent()){
                    OrderModel orderModel = new OrderModel();
                    orderModel.setOrderDate(LocalDateTime.now());
                    orderModel.setOrderId(isCartAvailable.get().getOrderId());
                    orderModel.setPrice(isCartAvailable.get().getPrice());
                    orderModel.setQuantity(isCartAvailable.get().getQuantity());
                    orderModel.setUserId(isCartAvailable.get().getUserId());
                    orderModel.setCartId(cartId);
                    orderModel.setCancel(false);
                Optional<AddressModel> isAddressAvailable = addressrepository.findById(cartId);
                if (isAddressAvailable.get().getUserId() = isCartAvailable.get().getCartId();


                }

            }

            }
        throw new OrderNotFoundException(400, "token is wrong");
    }
}
