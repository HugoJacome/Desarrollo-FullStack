package com.unir.operador.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.unir.operador.model.pojo.Customer;
import com.unir.operador.model.request.CustomerRequest;
import com.unir.operador.service.CustomersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomersController {
	
	private final CustomersService service;

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers(@RequestHeader Map<String, String> headers) {
		log.info("headers: {}", headers);
		List<Customer> customers = service.getCustomers();

		if (customers != null) {
			return ResponseEntity.ok(customers);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {

		log.info("Request received for product {}", customerId);
		Customer customer = service.getCustomer(customerId);

		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {

		Boolean removed = service.removeCustomer(customerId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest request) {
		Customer createdCustomer = service.createCustomer(request);
		if (createdCustomer != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
