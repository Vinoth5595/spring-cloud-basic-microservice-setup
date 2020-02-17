package com.microservices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>  {

}
