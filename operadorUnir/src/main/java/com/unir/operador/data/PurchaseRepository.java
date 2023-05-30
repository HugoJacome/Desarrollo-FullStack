package com.unir.operador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.operador.model.pojo.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findByProductId(Long productId);
}
