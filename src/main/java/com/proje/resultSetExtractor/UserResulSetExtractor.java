package com.proje.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.proje.model.User;
import com.proje.model.UserDetail;

public class UserResulSetExtractor implements ResultSetExtractor<User>{

	public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		User user = null;
		
		UserDetail userDetail = null;
		
		if(resultSet.next()) {
			
			int userId = resultSet.getInt("");
			
			String username = resultSet.getString("");
			
			String password = resultSet.getString("");
			
			Date creationDate = resultSet.getDate("");
			
			int userDetailId = resultSet.getInt("");
			
			String firstName = resultSet.getString("");
			
			String lastName  = resultSet.getString("");
			
			Date birthOfDate = resultSet.getDate("");
			
			user = new User(userId, username, password, creationDate);
			
			userDetail = new UserDetail(userDetailId, firstName, lastName, birthOfDate);
			
			user.setUserDetail(userDetail);

		}
		
		return user;
	}

	
}
