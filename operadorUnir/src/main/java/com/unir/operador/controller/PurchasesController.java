package com.unir.operador.controller;

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

import com.unir.operador.model.pojo.Purchase;
import com.unir.operador.model.request.PurchaseRequest;
import com.unir.operador.service.PurchasesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PurchasesController {
	
	private final PurchasesService service;

	@GetMapping("/purchases")
	public ResponseEntity<List<Purchase>> getPurchases(@RequestHeader Map<String, String> headers) {
		log.info("headers: {}", headers);
		List<Purchase> purchases = service.getPurchases();
		if (purchases != null) {
			return ResponseEntity.ok(purchases);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/purchases/{purchaseId}")
	public ResponseEntity<Purchase> getPurchase(@PathVariable String purchaseId) {

		log.info("Request received for purchase {}", purchaseId);
		Purchase purchase = service.getPurchase(purchaseId);

		if (purchase != null) {
			return ResponseEntity.ok(purchase);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/purchases/{purchaseId}")
	public ResponseEntity<Void> deletePurchaser(@PathVariable String purchaseId) {

		Boolean removed = service.removePurchase(purchaseId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/purchases")
	public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest request) {
		Purchase createdPurchase = service.createPurchase(request);
		if (createdPurchase != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchase);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
