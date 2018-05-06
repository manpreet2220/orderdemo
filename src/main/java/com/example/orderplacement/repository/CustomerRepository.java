package com.example.orderplacement.repository;

import com.example.orderplacement.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  @Query("select new Customer (c.name,c.email,c.phoneNumber,c.customerId) " +
          "from Customer c")
  List<Customer> findAllCustomers();
}