package com.unir.buscador.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.buscador.model.pojo.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
