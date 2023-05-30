package com.unir.operador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.operador.data.CustomerRepository;
import com.unir.operador.model.pojo.Customer;
import com.unir.operador.model.request.CustomerRequest;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public List<Customer> getCustomers() {
		List<Customer> products = repository.findAll();
		return products.isEmpty() ? null : products;
	}

	@Override
	public Customer getCustomer(String customerId) {
		return repository.findById(Long.valueOf(customerId)).orElse(null);
	}

	@Override
	public Boolean removeCustomer(String customerId) {
		Customer customer = repository.findById(Long.valueOf(customerId)).orElse(null);
		if (customer != null) {
			repository.delete(customer);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Customer createCustomer(CustomerRequest request) {

		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getLastName().trim())
				&& StringUtils.hasLength(request.getUsername().trim())
				&& StringUtils.hasLength(request.getPassword().trim())
				&& StringUtils.hasLength(request.getEmail().trim())) {
			Customer customer = Customer.builder().name(request.getName()).lastName(request.getLastName())
					.username(request.getUsername()).password(request.getPassword()).email(request.getEmail()).build();
			return repository.save(customer);
		} else {
			return null;
		}
	}

}
