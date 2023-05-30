package com.unir.buscador.service;

import java.util.List;

import com.unir.buscador.model.pojo.Category;
import com.unir.buscador.model.request.CategoryRequest;

public interface CategoriesService {

	List<Category> getCategories();

	Category getCategory(String categoryId);

	Boolean removeCategory(String categoryId);

	Category createCategory(CategoryRequest request);
}
