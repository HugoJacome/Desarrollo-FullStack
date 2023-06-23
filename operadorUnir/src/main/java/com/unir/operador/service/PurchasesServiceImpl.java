package com.unir.operador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.operador.data.PurchaseRepository;
import com.unir.operador.facade.ProductsFacade;
import com.unir.operador.model.pojo.Customer;
import com.unir.operador.model.pojo.Purchase;
import com.unir.operador.model.request.DecreaseProductRequest;
import com.unir.operador.model.request.PurchaseRequest;

@Service
public class PurchasesServiceImpl implements PurchasesService {

	@Autowired
	private PurchaseRepository repository;
	@Autowired
	private ProductsFacade productsFacade;

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
		if (
				request != null
				&& request.getProductId() != null
				&& request.getQuantity() != null
				&& request.getTotalAmount() != null
				&& StringUtils.hasLength(request.getCustomerName())
				&& StringUtils.hasLength(request.getCustomerLastName())
				&& StringUtils.hasLength(request.getCustomerEmail())
				&& StringUtils.hasLength(request.getCustomerPhone())
				&& StringUtils.hasLength(request.getCustomerAddress())
				&& StringUtils.hasLength(request.getCustomerCity())
			) {
			DecreaseProductRequest reqDrecreProduct = new DecreaseProductRequest(request.getProductId(), request.getQuantity());
			productsFacade.decreaseQuantityProduct(reqDrecreProduct);
			Purchase purchase = Purchase.builder().productId(request.getProductId())
					.customerName(request.getCustomerName())
					.customerLastName(request.getCustomerLastName())
					.customerPhone(request.getCustomerPhone())
					.customerEmail(request.getCustomerEmail())
					.customerCity(request.getCustomerCity())
					.customerAddress(request.getCustomerAddress())
					.quantity(request.getQuantity()).totalAmount(request.getTotalAmount()).build();
			try {
				return repository.save(purchase);
			} catch (Exception e) {
				//Suma cantidad restada anteriormente
				reqDrecreProduct.setQuantityToDecrease(-reqDrecreProduct.getQuantityToDecrease());
				productsFacade.decreaseQuantityProduct(reqDrecreProduct);
			}
			
		} 
		return null;
	}

}
