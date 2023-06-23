package com.unir.buscador.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.unir.buscador.model.pojo.Product;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
	List<Product> findByTitle(String title);

	Optional<Product> findById(String id);

	Product save(Product product);

	void delete(Product product);

	List<Product> findAll();
}
