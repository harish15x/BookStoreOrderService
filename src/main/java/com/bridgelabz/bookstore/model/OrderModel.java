package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.OrderDTO;
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
    private String emailId;
    private LocalDateTime orderDate;
    private int price;
    private int quantity;
    @OneToOne
    private AddressModel address;
    private long userId;
    private long bookId;
    private long cartId;
    private boolean cancel = false;


}
