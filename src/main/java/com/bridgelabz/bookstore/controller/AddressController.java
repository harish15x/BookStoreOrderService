package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.model.AddressModel;
import com.bridgelabz.bookstore.services.AddressService;
import com.bridgelabz.bookstore.services.IAddressService;
import com.bridgelabz.bookstore.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookstore")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @PostMapping("/inseraddress")
    public ResponseEntity<ResponseClass> inserAddress(@RequestBody AddressDTO addressDTO) {
        ResponseClass responseClass = addressService.insertAddress(addressDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/upddate/{addressId}")
    public ResponseEntity<ResponseClass> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO, @RequestHeader String token){
        ResponseClass responseClass = addressService.updateAddress(addressId, addressDTO, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/fetchalladdress")
    public ResponseEntity<List<?>> fetchAllAddress(@PathVariable Long addressId, @RequestHeader String token){
        List<AddressModel> responseClass = addressService.fetchAllAddress(addressId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{addressId}")
    public ResponseEntity<ResponseClass> deleteAddressById(@PathVariable Long addressId,@RequestHeader String token) {
        ResponseClass responseClass = addressService.deleteAddressById(addressId, token);
        return new ResponseEntity<>(responseClass ,HttpStatus.OK);
    }

    @GetMapping("/getaddress/{addressId}")
    public ResponseEntity<ResponseClass> getAddressById(@PathVariable Long addressId,@RequestHeader String token) {
        ResponseClass responseClass = addressService.getAddressById(addressId, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }


}
