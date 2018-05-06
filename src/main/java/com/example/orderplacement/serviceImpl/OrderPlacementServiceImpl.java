package com.example.orderplacement.serviceImpl;

import com.example.orderplacement.model.Customer;
import com.example.orderplacement.model.ServiceOrder;
import com.example.orderplacement.repository.CustomerRepository;
import com.example.orderplacement.repository.ServiceOrderRepository;
import com.example.orderplacement.service.OrderPlacementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderPlacementServiceImpl implements OrderPlacementService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ServiceOrderRepository serviceOrderRepository;

  Customer setDataOfCustomer(Map<String, String> requestParameters,Customer customer){
    String name = requestParameters.get("name");
    String email = requestParameters.get("email");
    String phone = requestParameters.get("phone");
    Long phoneNumber=null;
    customer.setName(name);
    customer.setEmail(email);
    if(!phone.isEmpty()){
      phoneNumber=Long.parseLong(phone);
      customer.setPhoneNumber(phoneNumber);
    }
    return customer;
  }
  String getServiceTypesString(String[] str){
    String serviceType="";
    for (String type:str){
      serviceType=serviceType+type+",";
    }
    return serviceType;
  }

  ServiceOrder setDataOfServiceOrder(Map<String, String> requestParameters,String[] serviceTypes,ServiceOrder serviceOrder) throws ParseException {
    String fromAddress = requestParameters.get("fromAddress");
    String toAddress = requestParameters.get("toAddress");
    String serviceDateInString = requestParameters.get("serviceDate");
    String note = requestParameters.get("note");
    Date serviceDate = null;
    if (!serviceDateInString.isEmpty()) {
      serviceDate = new SimpleDateFormat("yyyy-MM-dd").parse(serviceDateInString);
    }
    String serviceType=getServiceTypesString(serviceTypes);
    serviceOrder.setFromAddress(fromAddress);
    serviceOrder.setToAddress(toAddress);
    serviceOrder.setServiceType(serviceType);
    serviceOrder.setNote(note);
    serviceOrder.setServiceDate(serviceDate);
    return serviceOrder;
  }

  @Override
  public Long saveOrderFromCustomer(Map<String, String> requestParameters,String[] serviceTypes) throws ParseException, IOException {
    Customer customer = new Customer();
    customer=setDataOfCustomer(requestParameters,customer);
    customerRepository.save(customer);
    ServiceOrder serviceOrder = new ServiceOrder();
    serviceOrder=setDataOfServiceOrder(requestParameters,serviceTypes,serviceOrder);
    serviceOrder.setCustomer(customer);
    serviceOrderRepository.save(serviceOrder);
    Long serviceOrderId = serviceOrder.getServiceOrderId();
    return serviceOrderId;
  }

  @Override
  public List<Customer> getAllCustomers() {
    List<Customer> customersList = customerRepository.findAllCustomers();
    return customersList;
  }

  @Override
  public List<Object[]> getAllServiceOrders() {
    List<Object[]> serviceOrderList=serviceOrderRepository.findAllServiceOrders();
    return serviceOrderList;
  }

  @Override
  public ServiceOrder findServiceOrderById(String serviceOrderId) {
    ServiceOrder serviceOrder=serviceOrderRepository.findByServiceOrderId(Long.parseLong(serviceOrderId));
    return serviceOrder;
  }

  @Override
  public Long updateServiceOrder(ServiceOrder serviceOrder, Map<String, String> requestParametes,String[] serviceTypes) throws ParseException {
    Customer customer=serviceOrder.getCustomer();
    customer=setDataOfCustomer(requestParametes,customer);
    customerRepository.save(customer);
    serviceOrder=setDataOfServiceOrder(requestParametes,serviceTypes,serviceOrder);
    serviceOrder.setCustomer(customer);
    serviceOrderRepository.save(serviceOrder);
    Long serviceOrderId = serviceOrder.getServiceOrderId();
    return serviceOrderId;
  }

  @Override
  public void deleteServiceOrderById(String serviceOrderId) {
    if(!serviceOrderId.isEmpty()){
      serviceOrderRepository.deleteById(Long.parseLong(serviceOrderId));
      log.info("Service order deleted successfully");
    }

  }
}
