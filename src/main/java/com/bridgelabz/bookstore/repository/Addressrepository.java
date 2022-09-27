package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Addressrepository extends JpaRepository<AddressModel, Long> {
}
