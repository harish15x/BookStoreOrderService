package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.model.AddressModel;
import com.bridgelabz.bookstore.util.ResponseClass;

import java.util.List;

public interface IAddressService {
    ResponseClass insertAddress(AddressDTO addressDTO);

    ResponseClass updateAddress(Long addressId, AddressDTO addressDTO, String token);

    List<AddressModel> fetchAllAddress(Long addressId, String token);

    ResponseClass deleteAddressById(Long addressId, String token);

    ResponseClass getAddressById(Long addressId, String token);
}
