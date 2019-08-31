package com.proje.service;

import java.util.List;

import com.proje.model.UserDetail;

public interface UserDetailService {

	boolean save(int userId, UserDetail userDetail);

	boolean update(UserDetail userDetail);

	UserDetail findById(int id);

	List<UserDetail> findUserDetail();
}
