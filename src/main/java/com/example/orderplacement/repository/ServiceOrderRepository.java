package com.example.orderplacement.repository;

import com.example.orderplacement.model.Customer;
import com.example.orderplacement.model.ServiceOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Long> {


  @Query("Select sv.serviceOrderId,sv.customer.name,c.phoneNumber,sv.serviceType  from ServiceOrder sv " +
          "inner join sv.customer c order by sv.serviceOrderId desc")
  List<Object[]> findAllServiceOrders();

  ServiceOrder findByServiceOrderId(Long serviceOrderId);
}

