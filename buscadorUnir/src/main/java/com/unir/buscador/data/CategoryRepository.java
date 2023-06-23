package com.unir.buscador.data;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.unir.buscador.model.pojo.Category;

public interface CategoryRepository extends ElasticsearchRepository<Category, String> {

}
