package com.unir.operador.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.unir.operador.model.pojo.Product;
import com.unir.operador.model.request.DecreaseProductRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsFacade {
	@Value("${getProduct.url}")
	private String getProductUrl;
	
	@Value("${endpointProduct.url}")
	private String endpointProductUrl;

	private final RestTemplate restTemplate;

	public Product getProduct(String id) {

		try {
			return restTemplate.getForObject(String.format(getProductUrl, id), Product.class);
		} catch (HttpClientErrorException e) {
			log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
			return null;
		}
	}
	
	public void decreaseQuantityProduct(DecreaseProductRequest request) {
		final HttpEntity<DecreaseProductRequest> requestEntity = new HttpEntity<>(request);
		ResponseEntity<String> responseEntity = restTemplate.exchange(endpointProductUrl, HttpMethod.PATCH, requestEntity, String.class);
		if(!responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
			throw new HttpClientErrorException(responseEntity.getStatusCode());
		}
	}
}
