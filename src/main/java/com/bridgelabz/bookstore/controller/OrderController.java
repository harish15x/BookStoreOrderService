package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.services.IOrderService;
import com.bridgelabz.bookstore.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookstore")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<ResponseClass> placeOrder(@RequestBody OrderDTO orderDTO, @RequestParam Long cartId, @RequestParam Long addressId, @RequestHeader String token){
        ResponseClass responseClass = orderService.placeOrder(orderDTO, cartId, addressId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }


}
