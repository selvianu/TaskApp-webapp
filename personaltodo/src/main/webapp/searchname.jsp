<%@page import="com.cys.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Users</title>
</head>
<a href="home.jsp">Home</a>
<body>
	<%
	User namesearched = (User) request.getAttribute("searchName");
	//	out.println(namesearched);
	%>
	<div class="container">
		<div class="row">
			<div class="col-sm-6"></div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<table id="userTable"
					class="table table-bordered table-condensed table-striped"
					border="1">
					<thead>
						<tr>
							<th>User Id</th>
							<th>User Name</th>
							<th>Email</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><%=namesearched.getUserId()%>
							<td><%=namesearched.getUserName()%></td>
							<td><%=namesearched.getEmail()%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</body>
</html>