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
import java.util.List;
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

    @Autowired
    MailService mailService;


    @Override
    public ResponseClass placeOrder(OrderDTO orderDTO, Long cartId, Long addressId, String token) {
        boolean isCartPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isCartPresent) {
            Optional<OrderModel> isCartAvailable = orderRepository.findById(cartId);
            if (isCartPresent == isCartAvailable.isPresent());
            OrderModel orderModel = new OrderModel();
            if (isCartAvailable.isPresent()){
                    orderModel.setOrderDate(LocalDateTime.now());
                    orderModel.setOrderId(isCartAvailable.get().getOrderId());
                    orderModel.setPrice(isCartAvailable.get().getPrice());
                    orderModel.setQuantity(isCartAvailable.get().getQuantity());
                    orderModel.setUserId(isCartAvailable.get().getUserId());
                    orderModel.setCartId(cartId);
                    orderModel.setCancel(false);
                Optional<AddressModel> isAddressAvailable = addressrepository.findById(cartId);
                if (isCartPresent == isAddressAvailable.isPresent());
                orderModel.setAddress(isAddressAvailable.get());
                } else {
                throw new OrderNotFoundException(400, "Address does not match with the id");
            }
            orderRepository.save(orderModel);
            String body = "Your Order Placer with Order Id is :" + isCartAvailable.get().getOrderId();
            String subject = "Order Successfully Placed";
            mailService.send(isCartAvailable.get().getEmailId(), body, subject);
            return new ResponseClass(200, "Sucessfull", isCartAvailable.get());
            }
        throw new OrderNotFoundException(400, "token is wrong");
    }

    @Override
    public ResponseClass cancelOrder(Long orderId, String token) {
        boolean isCartPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isCartPresent){
            Optional<OrderModel> isCartAvailable = orderRepository.findById(orderId);
            if (isCartAvailable.isPresent()){
                isCartAvailable.get().setCancel(true);
                isCartAvailable.get().getBookId();
                isCartAvailable.get().getQuantity();
                orderRepository.delete(isCartAvailable.get());
                return new ResponseClass(200, "Sucessfull", isCartAvailable.get());
            }
            throw new OrderNotFoundException(400, "Cart not Found");
        }
        throw new OrderNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<OrderModel> getAllOrder(String token) {
        boolean isCartPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isCartPresent){
            List<OrderModel> isCartAvailable = orderRepository.findAll();
            if (isCartAvailable.size() > 0 ){
                return isCartAvailable;
            }
            throw new OrderNotFoundException(400, "Cart not available");
        }
        throw new OrderNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<OrderModel> getUserOrders(String token) {
        boolean isCartPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isCartPresent){
            List<OrderModel> isCartAvailable = orderRepository.findByUserId(token);
            if (isCartAvailable.size() > 0){
                return isCartAvailable;
            }
            throw new OrderNotFoundException(400, "Cart not available");
        }

        throw new OrderNotFoundException(400,"token is wrong");
    }


}
