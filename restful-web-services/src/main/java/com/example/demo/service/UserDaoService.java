package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	private static int userCount = 3;

	static {
		userList.add(User.builder().name("Java").id(1).birthDate(new Date()).build());
		userList.add(User.builder().name("Python").id(2).birthDate(new Date()).build());
		userList.add(User.builder().name("Dart").id(3).birthDate(new Date()).build());
	}

	public List<User> findAll() {
		return userList;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator it = userList.iterator();
		while(it.hasNext()) {
			User user = (User)it.next();
			if(user.getId()==id) {
				it.remove();
				return user;
			}
			
		}
		return null;
	}
}
