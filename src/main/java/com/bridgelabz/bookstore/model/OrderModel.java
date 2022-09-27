package com.bridgelabz.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="ordertable")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private LocalDateTime orderDate;
    private int price;
    private int quantity;
    private String address;
    private long userId;
    private long bookId;
    private long cartId;
    private boolean cancel = false;

}
