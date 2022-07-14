package com.cys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cys.dao.UserDao;
import com.cys.model.User;
import com.cys.validator.Validation;

@Controller
public class UserController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	UserDao userDao;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

//User Registration 
	@GetMapping("/register")
	public String registerUser(@RequestParam("id") int id, @RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("email") String email) {

		User user = new User();
		user.setUserId(id);
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserId(id);
		Integer userId = user.getUserId();
		user.setUserName(userName);
		String name = user.getUserName();
		user.setPassword(password);
		String password2 = user.getPassword();
		user.setEmail(email);
		String email2 = user.getEmail();
		System.out.println("Set and Get name" + user.getUserName());
		// validating the user values
		Validation.nameValidation(user);
		Validation.passwordValidator(user);
		Validation.isValidMailId(user);
		System.out.println(user.getUserName() + user.getEmail());
		userDao.save(user);
		return "login.jsp";
	}

//Login- valid details
	@GetMapping("/login")
	public String loginUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		User user = new User();
		user.setUserName(userName);
		String name = user.getUserName();
		user.setPassword(password);
		String password2 = user.getPassword();
		String loginPassword = userDao.login(user);
		// password verified from DB to check
		// Only if the values matches with values of DB
		if (name.equals(userName) && (password2.equals(loginPassword))) {
			user.setUserName(userName);
			user.setPassword(password);
			System.out.println(user.getUserName() + user.getEmail());
			return "redirect:listofusers";// redirects to the respective method(listofusers - controller)
		} else {
			System.out.println("Invalid data..");
		}
		return "error.jsp";
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam("userid") Integer userId, Model model) {
		System.out.println(userId);
		// invokes the findOne(userid)for which we find the record so that that record
		// can be used to edit.
		User user = userDao.findOne(userId);
		model.addAttribute("USER_DETAILS", user);

		return "update.jsp";
	}

	@GetMapping("/update")
	public String updateUser(@RequestParam("userid") Integer userId, @RequestParam("email") String email, Model model) {
		System.out.println(userId + email);
		// use encapsulation properly
		User user = new User();
		user.setUserId(userId);
		user.setEmail(email);

		int updateRows = userDao.update(user);
		// Model addAttribute = model.addAttribute(updateRows);
		// model.addAttribute("USER_LIST", users);
		return "redirect:listofusers";
	}

//displaying the data from DB
	@GetMapping("/listofusers")
	// As all the datas to be displayed in view(jsp) setting all values in Model
	public String getAllUser(Model model) {
//	public List<User> getAllUsers() {
		System.out.println("getting datas");
		User u1 = new User();
		// System.out.println(u1.userName);
		// System.out.println(u1.email);
		List<User> users = userDao.listUsers();// listofUsers
		// values set in model object-key and value
		model.addAttribute("USER_LIST", users);
		// using "USER_LIST" - key,values retrieved in jsp
		return "listusers.jsp";
	}

//to find the user by id
	@GetMapping("/findUserbyId")
	public String findUserById(@RequestParam("userId") Integer userId, Model model) {
		String name = userDao.findById(userId);
		// if no user found in the given name, exception
		if (name == null) {
			throw new EmptyResultDataAccessException("User not found", userId);
		} else {
			System.out.println("User Found" + name);
			model.addAttribute("userFound", name);
		}
		return "success.jsp";
	}

//To find the name
	@GetMapping("/searchByName")
	public String searchName(@RequestParam("userName") String userName, Model model) {
		User searchName = userDao.searchName(userName);
		System.out.println("Searched user record" + searchName);
		model.addAttribute("searchName", searchName);
		return "searchname.jsp";
	}

//To delete a particular record with id
	@GetMapping("/delete")
	// pass the same variable name as in Request Param in jsp with case sensitive.
	public String deletebyId(@RequestParam("userId") Integer userId) {
		System.out.println("deleting..");
		int delete = userDao.delete(userId);
		return "redirect:listofusers";
	}
}
