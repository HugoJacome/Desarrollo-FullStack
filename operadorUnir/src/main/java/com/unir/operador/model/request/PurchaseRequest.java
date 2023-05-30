package com.unir.operador.model.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

	private Long productId;
	private Long customerId;
	private Integer quantity;
	private BigDecimal totalAmount;
}
