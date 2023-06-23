package com.unir.buscador.service;

import java.util.ArrayList;
import java.util.Date;
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
		Iterable<Category> categories = repository.findAll();
		List<Category> lista = new ArrayList<>();
		System.out.println(categories);
		for (Category category : categories) {
			System.out.println(category);
			lista.add(category);
		}
		return lista.isEmpty() ? null : lista;
	}

	@Override
	public Category getCategory(String categoryId) {
		return repository.findById(categoryId).orElse(null);
	}

	@Override
	public Boolean removeCategory(String categoryId) {
		Category category = repository.findById(categoryId).orElse(null);
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
			Category category = Category.builder().name(request.getName()).description(request.getDescription()).createdAt(new Date()).build();
			return repository.save(category);
		} else {
			return null;
		}
	}

}
