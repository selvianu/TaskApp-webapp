package com.cys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cys.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		// getting the values to map DB
		int user_Id = rs.getInt("id");// the fields names should be as like in the database
		String user_Name = rs.getString("name");
		String user_email = rs.getString("email");
		// setting above values to display(in user-object)
		user.setUserId(user_Id);
		user.setUserName(user_Name);
		user.setEmail(user_email);

		System.out.println(user_Id + user_Name + user_email);
		return user;
	}

}
