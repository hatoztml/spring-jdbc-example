package com.proje.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proje.model.User;
import com.proje.repository.UserRepository;
import com.proje.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public boolean save(User user) {
		return this.userRepository.save(user);
	}

	public boolean update(User user) {
		return this.userRepository.update(user);
	}

	public boolean deleteById(int id) {
		return this.userRepository.deleteById(id);
	}

	public User findById(int id) {
		return this.userRepository.findById(id);
	}

	public User findWithUserDetailById(int id) {
		return this.userRepository.findWithUserDetailById(id);
	}

	public List<User> findUsers() {
		return this.userRepository.findUsers();
	}

	
}
