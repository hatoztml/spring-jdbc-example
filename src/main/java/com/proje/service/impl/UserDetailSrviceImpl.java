package com.proje.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proje.model.UserDetail;
import com.proje.repository.UserDetailRepository;
import com.proje.service.UserDetailService;

@Service
public class UserDetailSrviceImpl implements UserDetailService {

	@Autowired
	private UserDetailRepository userDetailRepository;
	
	public boolean save(int userId, UserDetail userDetail) {
		// TODO Auto-generated method stub
		return this.userDetailRepository.save(userId, userDetail);
	}

	public boolean update(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return this.userDetailRepository.update(userDetail);
	}

	public UserDetail findById(int id) {
		// TODO Auto-generated method stub
		return this.userDetailRepository.findById(id);
	}

	public List<UserDetail> findUserDetail() {
		// TODO Auto-generated method stub
		return this.userDetailRepository.findUserDetail();
	}

	
}
