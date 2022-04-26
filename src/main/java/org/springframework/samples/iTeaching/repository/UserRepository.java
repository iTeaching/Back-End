package org.springframework.samples.iTeaching.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.iTeaching.model.User;


public interface UserRepository extends  CrudRepository<User, String>{
	
	
}
