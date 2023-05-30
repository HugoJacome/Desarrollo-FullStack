package com.unir.buscador.controller;

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

import com.unir.buscador.model.pojo.Category;
import com.unir.buscador.model.request.CategoryRequest;
import com.unir.buscador.service.CategoriesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoriesController {
	
	private final CategoriesService service;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getCategories(@RequestHeader Map<String, String> headers) {
		log.info("headers: {}", headers);
		List<Category> categories = service.getCategories();

		if (categories != null) {
			return ResponseEntity.ok(categories);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable String categoryId) {

		log.info("Request received for product {}", categoryId);
		Category category = service.getCategory(categoryId);
		if (category != null) {
			return ResponseEntity.ok(category);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
		Boolean removed = service.removeCategory(categoryId);
		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/categories")
	public ResponseEntity<Category> createCustomer(@RequestBody CategoryRequest request) {
		Category createdCategory = service.createCategory(request);
		if (createdCategory != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
