package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.exception.AddressNotFoundException;
import com.bridgelabz.bookstore.exception.OrderNotFoundException;
import com.bridgelabz.bookstore.model.AddressModel;
import com.bridgelabz.bookstore.repository.Addressrepository;
import com.bridgelabz.bookstore.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    Addressrepository addressrepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseClass insertAddress(AddressDTO addressDTO) {
        AddressModel addressModel = new AddressModel(addressDTO);
        addressModel.setUserId(addressModel.getUserId());
        addressrepository.save(addressModel);
        return new ResponseClass(200, "Sucessfull", addressModel);
    }

    @Override
    public ResponseClass updateAddress(Long addressId, AddressDTO addressDTO, String token) {
        boolean isAddressPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isAddressPresent){
            Optional<AddressModel> isAddressAvailable = addressrepository.findById(addressId);
            if (isAddressAvailable.isPresent()){
                isAddressAvailable.get().setName(addressDTO.getName());
                isAddressAvailable.get().setPhoneNumber(addressDTO.getPhoneNumber());
                isAddressAvailable.get().setAddress(addressDTO.getAddress());
                isAddressAvailable.get().setPincode(addressDTO.getPincode());
                isAddressAvailable.get().setLocality(addressDTO.getLocality());
                isAddressAvailable.get().setLandmark(addressDTO.getLandmark());
                isAddressAvailable.get().setCity(addressDTO.getCity());
                addressrepository.save(isAddressAvailable.get());
                return new ResponseClass(200, "Sucessfull", isAddressAvailable.get());
            }
            throw new AddressNotFoundException(400, "Address not Available");
        }
        throw new AddressNotFoundException(400, "Token is Wrong");
    }

    @Override
    public List<AddressModel> fetchAllAddress(Long addressId, String token) {
        boolean isAddressPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isAddressPresent){
            List<AddressModel> isAddressAvailable = addressrepository.findAll();
            if (isAddressAvailable.size() > 0 )
                return isAddressAvailable;
        }
        throw new AddressNotFoundException(400, "Address not available");
    }

    @Override
    public ResponseClass deleteAddressById(Long addressId, String token) {
        boolean isAddressPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isAddressPresent){
            Optional<AddressModel> isAddressAvailable = addressrepository.findById(addressId);
            if (isAddressAvailable.isPresent()){
                addressrepository.delete(isAddressAvailable.get());
                return new ResponseClass(200, "SUcessfull", isAddressAvailable.get());
            }
            throw new AddressNotFoundException(400, "AddressNot not availale");
        }
        throw new AddressNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseClass getAddressById(Long addressId, String token) {
        boolean isAddressPresent = restTemplate.getForObject("http://localhost:8095/bookstore/validate/" + token, Boolean.class);
        if (isAddressPresent){
            Optional<AddressModel> isAddressAvailable = addressrepository.findById(addressId);
            if (isAddressAvailable.isPresent()){
                addressrepository.save(isAddressAvailable.get());
                return new ResponseClass(200, "Sucessfull", isAddressAvailable.get());
            }
            throw new AddressNotFoundException(400, "Address not available");
        }
        throw new AddressNotFoundException(400, "Token is wrong");
    }


}
