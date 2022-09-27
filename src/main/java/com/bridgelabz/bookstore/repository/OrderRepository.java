package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByUserId(String token);
}
