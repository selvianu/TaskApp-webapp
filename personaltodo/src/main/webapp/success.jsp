<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
	<h1>Success Page!.....</h1>
	<%
	String name = (String) request.getAttribute("userName");
	out.println(name);
	%>
	<p>User Name: ${userFound}</p>
</body>
</html>