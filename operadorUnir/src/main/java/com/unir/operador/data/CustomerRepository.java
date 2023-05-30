package com.unir.operador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.operador.model.pojo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);
}
