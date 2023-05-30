package com.unir.operador.service;

import java.util.List;

import com.unir.operador.model.pojo.Purchase;
import com.unir.operador.model.request.PurchaseRequest;

public interface PurchasesService {

	List<Purchase> getPurchases();

	Purchase getPurchase(String purchaseId);

	Boolean removePurchase(String customerId);

	Purchase createPurchase(PurchaseRequest request);
}
