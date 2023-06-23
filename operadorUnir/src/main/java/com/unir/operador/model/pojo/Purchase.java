package com.unir.operador.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "product_id", nullable = false)
	private String productId;
	
	@Column(name = "purchase_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "total_amount", nullable = false, scale = 2, precision = 10)
	private BigDecimal totalAmount;
	
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	
	@Column(name = "customer_last_name", nullable = false)
	private String customerLastName;
	
	@Column(name = "customer_email", nullable = false)
	private String customerEmail;
	
	@Column(name = "customer_phone", nullable = false)
	private String customerPhone;
	
	@Column(name = "customer_address", nullable = false)
	private String customerAddress;
	
	@Column(name = "customer_city", nullable = false)
	private String customerCity;
	
	@Column(name = "comments")
	private String comments;

	@PrePersist
	public void preInsert() {
		purchaseDate = new Date();
	}
}