package com.unir.buscador.model.pojo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(indexName = "products", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

	@Id
	private String id;

	@Field(type = FieldType.Text, name = "title")
	private String title;

	@Field(type = FieldType.Double, name = "price")
	private Double price;

	@Field(type = FieldType.Text, name = "category_id")
	//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private String categoryId;

	@Field(type = FieldType.Search_As_You_Type, name = "description")
	private String description;
	
	@Field(type = FieldType.Text, name = "thumbnail")
	private String thumbnail;

	@Field(type = FieldType.Integer, name = "quantityAvaliable")
	private Integer quantityAvaliable;

	@Field(type = FieldType.Date, name = "createdAt")
	private Date createdAt;
}
