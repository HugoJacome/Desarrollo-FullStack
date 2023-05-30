package com.unir.buscador.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseProductRequest {

	private Integer productId;
	private Integer quantityToDecrease;
}
