package com.unir.buscador.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.buscador.model.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findByTitle(String title);
	Optional<Product> findByDescription(String description);
}
