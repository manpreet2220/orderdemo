package com.example.orderplacement.service;

import com.example.orderplacement.model.Customer;
import com.example.orderplacement.model.ServiceOrder;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OrderPlacementService {

    //Save order and customer.
    Long saveOrderFromCustomer(@RequestParam Map<String,String> requestParametes,String[] serviceTypes) throws ParseException, IOException;

    List<Customer> getAllCustomers();

    List<Object[]> getAllServiceOrders();

    ServiceOrder findServiceOrderById(String serviceOrderId);

    Long updateServiceOrder(ServiceOrder serviceOrder,@RequestParam Map<String, String> requestParametes,String[] serviceTypes) throws ParseException;

    void deleteServiceOrderById(String serviceOrderId);

}
