package com.unir.buscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.buscador.data.CategoryRepository;
import com.unir.buscador.model.pojo.Category;
import com.unir.buscador.model.request.CategoryRequest;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<Category> getCategories() {
		List<Category> categories = repository.findAll();
		return categories.isEmpty() ? null : categories;
	}

	@Override
	public Category getCategory(String categoryId) {
		return repository.findById(Integer.valueOf(categoryId)).orElse(null);
	}

	@Override
	public Boolean removeCategory(String categoryId) {
		Category category = repository.findById(Integer.valueOf(categoryId)).orElse(null);
		if (category != null) {
			repository.delete(category);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Category createCategory(CategoryRequest request) {

		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getDescription().trim())) {
			Category category = Category.builder().name(request.getName()).description(request.getDescription()).build();
			return repository.save(category);
		} else {
			return null;
		}
	}

}
