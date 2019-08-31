package com.proje.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.proje.model.User;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		int userId = rs.getInt("userId");
		
		String username = rs.getString("username");
		
		String password = rs.getString("password");
		
		Date creationDate = rs.getDate("creationDate");
		
		User user = new User(userId, username, password, creationDate);
		
		
		return user;
	}

	
}
