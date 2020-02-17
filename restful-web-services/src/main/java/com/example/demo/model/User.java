package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFilter("UserBeanFilter")
//Static Filtering
//@JsonIgnoreProperties({"id"})
public class User {
	private Integer id;
	@Size(min=2,max = 10, message = "Name should contains minimum 2 characters and maximim 10 characters.")
	private String name;
	@Past
	//Static Filtering
	//@JsonIgnore
	private Date birthDate;
	
}
