package com.cys.validator;

import java.util.regex.Pattern;

import com.cys.model.User;

public class Validation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// input
		User user = new User();

		// 2. name validation
		nameValidation(user);

		// 3. password Validation
		passwordValidator(user);
		isValidMailId(user);
	}

	public static void nameValidation(User user) {
		System.out.println(user);
		if ((user.getUserName().length() < 3)) {
			System.out.println("Enter valid user name");

		} else {
			System.out.println("Valid username");
		}

	}

	public static void passwordValidator(User user) {
		if ((user.getPassword().length() < 6) && (user.getPassword().contains(user.getUserName()))) {
			System.out.println("Password should contain greater than 6 characters and  shouldn't contain any names");
		} else {
			System.out.println("Valid Password");
		}

	}

	public static boolean isValidMailId(User user) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (user.getEmail() == null)
			return false;
		return pat.matcher(user.getEmail()).matches();
	}

}
