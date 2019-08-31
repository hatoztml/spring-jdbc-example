package com.proje.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import com.proje.model.UserDetail;
import com.proje.repository.UserDetailRepository;

@Transactional
public class UserDetailRepositoryImpl extends NamedParameterJdbcDaoSupport implements UserDetailRepository {

	public boolean save(int userId, UserDetail userDetail) {

		String querySaveUserDetail = "insert into userdetail(userDetailId, firstName, lastName, birthOfDate)"
				+ "values(:userDetailId, :firstName, :lastName, :birthOfDate)";

		String queryUpdateUser = "update user set userDetailId = :userDetailId where userId=:userId";

		try {

			SqlParameterSource sourceSaveUserDetail = new MapSqlParameterSource("userDetailId",
					userDetail.getUserDetailId()).addValue("firstName", userDetail.getFirstName())
							.addValue("lastName", userDetail.getLastName())
							.addValue("birthOfDate", userDetail.getBirthOfDate());

			this.getNamedParameterJdbcTemplate().update(querySaveUserDetail, sourceSaveUserDetail);

			SqlParameterSource sourceUpdateUser = new MapSqlParameterSource("userId", userId);

			this.getNamedParameterJdbcTemplate().update(queryUpdateUser, sourceUpdateUser);

		} catch (RuntimeException e) {
			System.out.println("HATA : " + e);
			return false;
		}
		return true;
	}

	public boolean update(UserDetail userDetail) {

		String query = "update userdetail set firstName = :firstName, lastName = :lastName, birthOfDate = :birthOfDate"
				+ "where userDetailId=:userDetailId";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("firstName", userDetail.getFirstName())
					.addValue("lastName", userDetail.getLastName()).addValue("birthOfDate", userDetail.getBirthOfDate())
					.addValue("userDetailId", userDetail.getUserDetailId());

			this.getNamedParameterJdbcTemplate().update(query, sqlParameterSource);

		} catch (RuntimeException e) {
			System.out.println("HATA  :  " + e);
		}

		return false;
	}

	public UserDetail findById(int id) {

		String query = "select * from userdetail where userDetailId=:userDetailId";

		UserDetail userDetail = null;

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userDetailId", id);

			userDetail = (UserDetail) this.getNamedParameterJdbcTemplate().query(query, sqlParameterSource,
					new BeanPropertyRowMapper<UserDetail>(UserDetail.class));

		} catch (RuntimeException e) {
			System.out.println("HATA  :" + e);
		}

		return userDetail;
	}

	public List<UserDetail> findUserDetail() {

		String query = "select * from userdetail";

		List<UserDetail> userDetails = null;

		try {

			userDetails = this.getNamedParameterJdbcTemplate().query(query,
					new BeanPropertyRowMapper(UserDetail.class));

		} catch (RuntimeException e) {
			System.out.println("HATA  :  " + e);
		}

		return userDetails;
	}

}
