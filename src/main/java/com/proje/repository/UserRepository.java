package com.proje.repository;

import java.util.List;

import com.proje.model.User;

public interface UserRepository {

	boolean save(User user);
	
	boolean update(User user);
	
	boolean deleteById(int id);
	
	User findById(int id);
	
	User findWithUserDetailById(int id);
	
	List<User> findUsers();
}
