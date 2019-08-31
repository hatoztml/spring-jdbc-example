package com.proje.service;

import java.util.List;

import com.proje.model.User;

public interface UserService {

	boolean save(User user);

	boolean update(User user);

	boolean deleteById(int id);

	User findById(int id);

	User findWithUserDetailById(int id);

	List<User> findUsers();
}
