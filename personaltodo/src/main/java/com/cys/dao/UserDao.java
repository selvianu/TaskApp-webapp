package com.cys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cys.mapper.UserMapper;
import com.cys.model.User;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void save(User saveUser) {
		String sql = "insert into users(id,name,password,email) values (?,?,?,?)";
		Object[] params = { saveUser.getUserId(), saveUser.getUserName(), saveUser.getPassword(), saveUser.getEmail() };
		int noOfRows = jdbcTemplate.update(sql, params);
		System.out.println(noOfRows + "inserted");

	}

	public String login(User userLogin) {
		System.out.println(userLogin.getUserName());
		String sql = "select password from users where name=?";
		String password = userLogin.getUserName();
		String passwordChecked = jdbcTemplate.queryForObject(sql, String.class, password);// single row single
																							// column//if only
		// one data/record is needed no need to
		// use mapper, can user String.class
		// and to pass the placeholder names
		// will retrieve the data.
		return passwordChecked;

	}

	public int delete(Integer userId) {
		System.out.println("reading query");
		String sql = "delete from users where id=?";
		System.out.println("Query executed");
		int oneRecordToDelete = jdbcTemplate.update(sql, userId);
		return oneRecordToDelete;
	}

	public int update(User u1) {
		String sql = "update users set email=? where id=?";
		Object[] params = { u1.getEmail(), u1.getUserId() };
		int noOfRows = jdbcTemplate.update(sql, params);
		System.out.println("noOfRowsUpdated : " + noOfRows);
		return noOfRows;

	}

	public List<User> listUsers() {
		String sql = "select * from users";
		List<User> usersList = jdbcTemplate.query(sql, new UserMapper());
		System.out.println(usersList);
		return usersList;
	}

	public User findOne(Integer userId) {
		String sql = "select * from users where id=?";
		User oneRecord = jdbcTemplate.queryForObject(sql, new UserMapper(), userId);
		System.out.println("Finding one record....." + oneRecord);
		return oneRecord;
	}

	@SuppressWarnings("unlikely-arg-type")
	public String findById(int userId) {
		String sql = "select name from users where id=?";
		System.out.println("user Id passed: " + userId);
		String queryForObject = null;
		try {
			queryForObject = jdbcTemplate.queryForObject(sql, String.class, userId);
			System.out.println(queryForObject);
		} catch (EmptyResultDataAccessException e) {
		}
		return queryForObject;

	}

	public User searchName(String name) {
		System.out.println("Searching name..");
		String sql = "select * from users where name=?";
		System.out.println("name....searching");
		User oneRecord = jdbcTemplate.queryForObject(sql, new UserMapper(), name);
		return oneRecord;

	}
}
