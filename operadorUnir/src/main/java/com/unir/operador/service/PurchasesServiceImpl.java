package com.unir.operador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.operador.data.PurchaseRepository;
import com.unir.operador.model.pojo.Customer;
import com.unir.operador.model.pojo.Purchase;
import com.unir.operador.model.request.PurchaseRequest;

@Service
public class PurchasesServiceImpl implements PurchasesService {

	@Autowired
	private PurchaseRepository repository;

	@Override
	public List<Purchase> getPurchases() {
		List<Purchase> purchases = repository.findAll();
		return purchases.isEmpty() ? null : purchases;
	}

	@Override
	public Purchase getPurchase(String purchaseId) {
		return repository.findById(Long.valueOf(purchaseId)).orElse(null);
	}

	@Override
	public Boolean removePurchase(String purchaseId) {
		Purchase purchase = repository.findById(Long.valueOf(purchaseId)).orElse(null);
		if (purchase != null) {
			repository.delete(purchase);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Purchase createPurchase(PurchaseRequest request) {
		if (request != null && request.getProductId() != null && request.getCustomerId() != null
				&& request.getQuantity() != null && request.getTotalAmount() != null) {
			Customer customer = Customer.builder().id(request.getCustomerId()).build();
			Purchase purchase = Purchase.builder().productId(request.getProductId()).customerId(customer)
					.quantity(request.getQuantity()).totalAmount(request.getTotalAmount()).build();
			return repository.save(purchase);
		} else {
			return null;
		}
	}

}
