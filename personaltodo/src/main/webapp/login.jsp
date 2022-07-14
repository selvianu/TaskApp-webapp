<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Login</h1>
	<form action="/login" method="get">
		User name: <input type="text" name="userName"><br />
		Password:<br> <input type="password" name="password"> <br>
		<input type="submit" value="Login">
	</form>
</body>
</html>