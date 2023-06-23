package com.unir.buscador.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.buscador.data.ProductDataAccessRepository;
import com.unir.buscador.model.pojo.Product;
import com.unir.buscador.model.request.CreateProductRequest;
import com.unir.buscador.model.request.DecreaseProductRequest;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductDataAccessRepository repository;

	@Override
	public List<Product> getProducts(String productId, String title, String description) {
		List<Product> products = repository.findProducts(productId, title, description);
		return products.isEmpty() ? null : products;
	}

	@Override
	public Boolean removeProduct(String productId) {
		Product product = repository.findById(productId).orElse(null);
		if (product != null) {
			repository.delete(product);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Product createProduct(CreateProductRequest request) {
		if (request != null && StringUtils.hasLength(request.getTitle().trim()) && request.getPrice() != null
				&& StringUtils.hasLength(request.getDescription().trim()) && request.getQuantityAvaliable() != null
				&& request.getCategoryId() != null) {
			Product product = Product.builder().title(request.getTitle()).price(request.getPrice())
					.description(request.getDescription()).quantityAvaliable(request.getQuantityAvaliable())
					.categoryId(request.getCategoryId()).createdAt(new Date()).build();
			return repository.save(product);
		} else {
			return null;
		}
	}

	@Override
	public Product updateQuantityProduct(DecreaseProductRequest request) throws Exception {
		if (request == null || request.getProductId() == null || request.getQuantityToDecrease() == null)
			return null;
		Product product = repository.findById(request.getProductId()).orElse(null);

		if (product != null) {
			if (request.getQuantityToDecrease() > product.getQuantityAvaliable()) {
				throw new Exception("Stock not available");
			}
			product.setQuantityAvaliable(product.getQuantityAvaliable() - request.getQuantityToDecrease());
			repository.save(product);
		}
		return product;
	}

}
