package com.unir.buscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.buscador.data.ProductRepository;
import com.unir.buscador.model.pojo.Category;
import com.unir.buscador.model.pojo.Product;
import com.unir.buscador.model.request.CreateProductRequest;
import com.unir.buscador.model.request.DecreaseProductRequest;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getProducts() {
		List<Product> products = repository.findAll();
		return products.isEmpty() ? null : products;
	}

	@Override
	public Product getProduct(String productId, String title, String description) {
		if (productId != null && StringUtils.hasLength(productId.trim()))
			return repository.findById(Integer.valueOf(productId)).orElse(null);
		else if (title != null && StringUtils.hasLength(title.trim()))
			return repository.findByTitle(title).orElse(null);
		else if (description != null && StringUtils.hasLength(description.trim()))
			return repository.findByDescription(description).orElse(null);

		return null;
	}

	@Override
	public Boolean removeProduct(String productId) {
		Product product = repository.findById(Integer.valueOf(productId)).orElse(null);
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
			Category category = Category.builder().id(request.getCategoryId()).build();
			Product product = Product.builder().title(request.getTitle()).price(request.getPrice())
					.description(request.getDescription()).quantityAvaliable(request.getQuantityAvaliable())
					.categoryId(category).build();
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
