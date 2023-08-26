package com.cg5labs.api.repository;

import com.cg5labs.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // SELECT * FROM customerdb WHERE email = ?
    Optional<Customer> findCustomerByEmail (String email);
}