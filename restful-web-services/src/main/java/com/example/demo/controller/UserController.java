package com.example.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.User;
import com.example.demo.model.Exception.UserNotFoundException;
import com.example.demo.service.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> getListOfUsers() {
		List<User> userList = userDaoService.findAll();

		return userList;
	}

	@GetMapping("/users/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") Integer id) {
		User result = userDaoService.findOne(id);
		//Dynamic Filtering
		MappingJacksonValue mapping = new MappingJacksonValue(result);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);
		mapping.setFilters(filters);
		if (result == null) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<User> user = new Resource(result);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getListOfUsers());
		user.add(linkTo.withRel("all-users"));

		return mapping;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedUser.getId())
				.toUri();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("location", location.toString());
		return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") Integer id) {
		User result = userDaoService.deleteUser(id);
		if (result == null) {
			throw new UserNotFoundException("id-" + id);
		}

		return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
	}

}
