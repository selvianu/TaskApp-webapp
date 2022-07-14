<%@page import="com.cys.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Update</h1>

<%
	User user = (User) request.getAttribute("USER_DETAILS");
		out.println(user);
	%>
		<form action="/update" method="get">
		ID : <input type="text" name="userid"
			value="<%=user.getUserId() %>"><br /> Email:<br>
		<input type="email" name="email" value="<%=user.getEmail() %>"> <br /> <input
			type="submit" value="Update">
	</form>
</body>
</html>	