package com.example.demo_es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Objects;
@Document(indexName = "dangdang",type = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address implements Serializable {
	@Id
	@Field(type = FieldType.Integer)
	private int id;
	@Field(type = FieldType.Text,analyzer ="ik_max_word")
	private String name;
	@Field(type = FieldType.Text,analyzer ="ik_max_word")
	private String local;
	@Field(type = FieldType.Text)
	private String phone;
	@Field(type = FieldType.Text)
	private String zip_code;
	@Field(type = FieldType.Keyword)
	private String user_id;

}
