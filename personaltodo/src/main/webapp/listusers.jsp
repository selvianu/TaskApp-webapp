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
<body>
	<%
	List<User> users = (List<User>) request.getAttribute("USER_LIST");
	//	out.println(users);
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
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (User user : users) {
						%>
						<tr>
							<td><%=user.getUserId()%>
							<td><%=user.getUserName()%></td>
							<td><%=user.getEmail()%></td>
							<td><a href="/edit?userid=<%=user.getUserId()%>">Edit</a></td>
							<td><a href="/delete?userId=<%=user.getUserId()%>">Delete</a></td>

						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</body>
</html>