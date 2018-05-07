package com.example.orderplacement.controller;

import com.example.orderplacement.model.Customer;
import com.example.orderplacement.model.ServiceOrder;
import com.example.orderplacement.service.OrderPlacementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class OrderPlacementController {

  @Autowired
  ObjectMapper mapper;

  @Autowired
  ResourceLoader resourceLoader;

  @Autowired
  OrderPlacementService orderPlacementService;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Failed to get data")  // 4041
  public class BadRequestException extends RuntimeException {
    // ...
  }

  //Method to save order placed by customer in database
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/orderplacement/save", method = RequestMethod.POST)
  Long saveOrderFromCustomer(@RequestParam Map<String, String> requestParametes,
                             @RequestParam("serviceTypes") String[] serviceTypes) throws IOException, ParseException {
    try {
      Long serviceOrderId = orderPlacementService.saveOrderFromCustomer(requestParametes,serviceTypes);
      log.info("Order has been placed with Id " + serviceOrderId);
      return serviceOrderId;
    } catch (Exception e) {
      throw new BadRequestException();
    }
  }

  //Method to get list of orders.
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/orderplacement/serviceOrders", method = RequestMethod.GET)
  List<Object[]> getAllServiceOrder() throws IOException, ParseException {
    try {
      List<Object[]> serviceOrderList = orderPlacementService.getAllServiceOrders();

      log.info("Size of customerList is " + serviceOrderList.size());
      return serviceOrderList;
    } catch (Exception e) {
      throw new BadRequestException();
    }
  }

  //Method to show selected service order
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/orderplacement/serviceOrders/{serviceOrderId}", method = RequestMethod.GET)
  ServiceOrder getServiceOrderById(@PathVariable("serviceOrderId") String serviceOrderId) throws IOException, ParseException {
    try {
      ServiceOrder serviceOrder = orderPlacementService.findServiceOrderById(serviceOrderId);

      return serviceOrder;
    } catch (Exception e) {
      throw new BadRequestException();
    }
  }

  //Method to edit selected service order
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/orderplacement/serviceOrders/{serviceOrderId}", method = RequestMethod.PUT)
  Long editServiceOrderById(@PathVariable("serviceOrderId") String serviceOrderId,
                            @RequestParam Map<String, String> requestParametes,
                            @RequestParam("serviceTypes") String[] serviceTypes
                                    ) throws IOException, ParseException {
    try {
      ServiceOrder serviceOrder=orderPlacementService.findServiceOrderById(serviceOrderId);
      Long id=orderPlacementService.updateServiceOrder(serviceOrder,requestParametes,serviceTypes);
      return id;
    } catch (Exception e) {
      throw new BadRequestException();
    }
  }

  //Method to delete selected service order
  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/orderplacement/serviceOrders/delete/{serviceOrderId}", method = RequestMethod.DELETE)
  void deleteServiceOrderById(@PathVariable("serviceOrderId") String serviceOrderId
  ) throws IOException, ParseException {
    try {
     orderPlacementService.deleteServiceOrderById(serviceOrderId);

    } catch (Exception e) {
      throw new BadRequestException();
    }
  }
}
