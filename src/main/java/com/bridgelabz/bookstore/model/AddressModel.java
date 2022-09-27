package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.AddressDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresstable")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    public String name;
    public long phoneNumber;
    public long pincode;
    public String locality;
    public String address;
    private String city;
    private String landmark;
    private long userId;

    public AddressModel(AddressDTO addressDTO) {
        this.name = addressDTO.getName();
        this.phoneNumber = addressDTO.getPhoneNumber();
        this.pincode = addressDTO.getPincode();
        this.locality = addressDTO.getLocality();
        this.address = addressDTO.getAddress();
        this.city = addressDTO.getCity();
        this.landmark = addressDTO.getLandmark();
    }

    public AddressModel() {

    }
}
