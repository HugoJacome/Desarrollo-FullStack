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

@Document(indexName = "categories", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {

	@Id
	private String id;

	@Field(type = FieldType.Text, name = "name")
	private String name;

	@Field(type = FieldType.Search_As_You_Type, name = "description")
	private String description;

	@Field(type = FieldType.Date, name = "createdAt")
	private Date createdAt;
}
