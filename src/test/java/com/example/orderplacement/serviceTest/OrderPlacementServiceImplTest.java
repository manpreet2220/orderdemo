package com.example.orderplacement.serviceTest;

import com.example.orderplacement.repository.CustomerRepository;
import com.example.orderplacement.repository.ServiceOrderRepository;
import com.example.orderplacement.service.OrderPlacementService;
import com.example.orderplacement.serviceImpl.OrderPlacementServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderPlacementServiceImplTest {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ServiceOrderRepository serviceOrderRepository;

  private OrderPlacementServiceImpl orderPlacementService;
  @Before
  public void setUp() throws Exception {
     orderPlacementService= new OrderPlacementServiceImpl();
     orderPlacementService.setCustomerRepository(customerRepository);
     orderPlacementService.setServiceOrderRepository(serviceOrderRepository);

  }


  @Test
  public void saveServiceOrder() throws IOException, ParseException {
    Map<String, String> map=new HashMap<>();
    map.put("name","Manpreet");
    map.put("phone","123434");
    map.put("email","manjndk@gmail.com");
    map.put("fromAddress","A1 street");
    map.put("toAddress","A2 street");
    map.put("serviceDate","");
    String[] serviceTypes=new String[3];
    serviceTypes[0]="Moving";
    serviceTypes[1]="Cleaning";
   Long orderId= orderPlacementService.saveOrderFromCustomer(map,serviceTypes);
    assertNotNull(orderId);

  }
}
