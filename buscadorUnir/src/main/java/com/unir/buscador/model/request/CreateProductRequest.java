package com.unir.buscador.model.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

	private String title;
	private BigDecimal price;
	private Integer categoryId;
	private String description;
	private Integer quantityAvaliable;
}
