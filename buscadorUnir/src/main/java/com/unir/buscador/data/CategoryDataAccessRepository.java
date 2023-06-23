package com.unir.buscador.data;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import com.unir.buscador.model.pojo.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryDataAccessRepository {
	// Esta clase (y bean) es la unica que usan directamente los servicios para
	// acceder a los datos.
	@Autowired
	private final ProductRepository productRepository;
	private final ElasticsearchOperations elasticClient;

	private final String[] descriptionSearchFields = { "description", "description._2gram", "description._3gram" };

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Boolean delete(Product product) {
		productRepository.delete(product);
		return Boolean.TRUE;
	}

	public List<Product> findProducts(String productId, String title, String description) {

		BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

		if (!StringUtils.isEmpty(productId)) {
			querySpec.must(QueryBuilders.termQuery("id", productId));
		}

		if (!StringUtils.isEmpty(title)) {
			querySpec.must(QueryBuilders.matchQuery("title", title));
		}

		if (!StringUtils.isEmpty(description)) {
			querySpec.must(QueryBuilders.multiMatchQuery(description, descriptionSearchFields).type(Type.BOOL_PREFIX));
		}

		// Si no he recibido ningun parametro, busco todos los elementos.
		if (!querySpec.hasClauses()) {
			querySpec.must(QueryBuilders.matchAllQuery());
		}

		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(querySpec);

		Query query = nativeSearchQueryBuilder.build();
		SearchHits<Product> result = elasticClient.search(query, Product.class);
		return result.getSearchHits().stream().map(SearchHit::getContent).toList();
	}

	public Optional<Product> findById(String id) {
		return productRepository.findById(id);
	}
}
