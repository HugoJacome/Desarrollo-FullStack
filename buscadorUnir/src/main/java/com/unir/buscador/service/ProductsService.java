package com.unir.buscador.service;

import java.util.List;

import com.unir.buscador.model.pojo.Product;
import com.unir.buscador.model.request.CreateProductRequest;
import com.unir.buscador.model.request.DecreaseProductRequest;

public interface ProductsService {

	List<Product> getProducts(String title, String description);
	
	Product getProduct(String productId);

	Boolean removeProduct(String customerId);

	Product createProduct(CreateProductRequest request);
	
	Product updateQuantityProduct(DecreaseProductRequest request) throws Exception;
}
