package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.services.IOrderService;
import com.bridgelabz.bookstore.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/cancelorder/{orderId}")
    public ResponseEntity<ResponseClass> cancelOrder(@PathVariable Long orderId,@RequestHeader String token){
        ResponseClass responseClass = orderService.cancelOrder(orderId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/getallorders")
    public ResponseEntity<List<?>> getAllOrders(@RequestHeader String token){
        List<OrderModel> responseClass = orderService.getAllOrder(token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/fetchuserorders")
    public ResponseEntity<List<?>> getUserOrders(@RequestHeader String token){
        List<OrderModel> responseClass = orderService.getUserOrders(token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

}
