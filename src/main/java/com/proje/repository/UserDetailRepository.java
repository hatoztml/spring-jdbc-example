package com.proje.repository;

import java.util.List;

import com.proje.model.UserDetail;

public interface UserDetailRepository {

	boolean save(int userId, UserDetail userDetail);
	
	boolean update(UserDetail userDetail);
	
	UserDetail findById(int id);
	
	List<UserDetail> findUserDetail();
}
