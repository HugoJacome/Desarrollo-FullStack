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

	private String productId;
	private Integer quantity;
	private BigDecimal totalAmount;
	private String customerName;
	private String customerLastName;
	private String customerEmail;
	private String customerPhone;
	private String customerAddress;
	private String customerCity;
	private String comments;
}
