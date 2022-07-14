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

	<form action="/searchByName" method="get">
		Name: <input type="text" name="userName"> <br /> <input
			type="submit" value="Search">
	</form>
	<form action="/register" method="get">
		ID :<br /> <input type="text" name="id"><br /> User name :<br />
		<input type="text" name="userName"><br /> Password:<br /> <input
			type="password" name="password"> <br />E-mail: <br /> <input
			type="email" name="email"><br /> <input type="submit"
			value="Add">
	</form>

</body>
</html>