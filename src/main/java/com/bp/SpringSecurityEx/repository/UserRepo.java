package com.bp.SpringSecurityEx.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bp.SpringSecurityEx.pojo.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
	
	User findByName(String name);
	List<User> findAll();
}
