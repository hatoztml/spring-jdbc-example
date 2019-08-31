package com.proje.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.proje.model.User;
import com.proje.model.UserDetail;
import com.proje.repository.UserRepository;
import com.proje.resultSetExtractor.UserResulSetExtractor;
import com.proje.rowmapper.UserRowMapper;

public class UserRepositoryImpl extends NamedParameterJdbcDaoSupport implements UserRepository {

	public boolean save(User user) {

		String query = "insert into user(userId,username,password,creationDate)"
				+ "values(:userId,:username,:password,:creationDate)";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", user.getUserId())
					.addValue("username", user.getUsername()).addValue("password", user.getPassword())
					.addValue("creationDate", user.getCreationDate());
			this.getNamedParameterJdbcTemplate().update(query, sqlParameterSource);
		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}
		return true;
	}

	public boolean update(User user) {

		String query = "update user set username = :username, password = :password where userId = :userId";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("username", user.getUsername())
					.addValue("password", user.getPassword()).addValue("userId", user.getUserId());
			this.getNamedParameterJdbcTemplate().update(query, sqlParameterSource);
		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}
		return true;
	}

	public boolean deleteById(int id) {

		String queryFindUser = "select userDetailId from user where userId=:userId";

		String queryDeleteUser = "delete from user where userId=:userId";

		String queryDeleteUserDetail = "delete from userdetail where userDetailId=:userDetailId";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", id);

			Integer userDetailId = this.getNamedParameterJdbcTemplate().query(queryFindUser, sqlParameterSource,
					new ResultSetExtractor<Integer>() {

						public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

							Integer userDetailId = rs.getInt("userDetailId");

							return userDetailId;
						}

					});

			SqlParameterSource sourceDeleteUser = new MapSqlParameterSource("userId", id);

			this.getNamedParameterJdbcTemplate().update(queryDeleteUser, sourceDeleteUser);

			SqlParameterSource sourceDeleteUserDetail = new MapSqlParameterSource("userDetailId",
					userDetailId.intValue());

			this.getNamedParameterJdbcTemplate().update(queryDeleteUserDetail, sourceDeleteUserDetail);

		} catch (RuntimeException e) {
			System.out.println("HATA : " + e);
		}

		return true;
	}

	public User findById(int id) {

		String query = "select * from user where userId = :userId";
		User user = null;

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", id);

			user = this.getNamedParameterJdbcTemplate().queryForObject(query, sqlParameterSource,
					new BeanPropertyRowMapper<User>(User.class));

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}
		return user;
	}

	public User findWithUserDetailById(int id) {

		String query = "select * from user u left join userdetail ud on(u.userDetailId = ud.userDetailId) where userId = :userId";

		User user = null;

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", id);

			user = this.getNamedParameterJdbcTemplate().query(query, sqlParameterSource, new UserResulSetExtractor());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}
		return user;
	}

	public List<User> findUsers() {

		String query = "select * from user";

		List<User> users = null;

		try {

			users = this.getNamedParameterJdbcTemplate().query(query, new UserRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return users;
	}

}
