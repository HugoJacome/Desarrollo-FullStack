package com.unir.operador.service;

import java.util.List;

import com.unir.operador.model.pojo.Customer;
import com.unir.operador.model.request.CustomerRequest;

public interface CustomersService {

	List<Customer> getCustomers();

	Customer getCustomer(String customerId);

	Boolean removeCustomer(String customerId);

	Customer createCustomer(CustomerRequest request);
}
