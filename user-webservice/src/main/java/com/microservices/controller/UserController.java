package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.beans.User;
import com.microservices.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/all")
	public Iterable<User> getUser() {
		return userRepository.findAll();
	}

	@PostMapping("/new")
	public ResponseEntity<User> createUser(@RequestBody User u) {
		userRepository.save(u);
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public User createUser(@PathVariable("id") int id) {
		return userRepository.findById(id).get();
	}

	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deletePost(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
	}	
}
