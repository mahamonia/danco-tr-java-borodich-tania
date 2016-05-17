<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Administrator</title>
<link rel="stylesheet" href="_style.css" type="text/css">
</head>
<body>
	<h2>Hotel Administrator</h2>
	<h3>View room</h3>
	<div class="menu">
		<ul>
			<li><a href="view.jsp">View data</a></li>
			<li><a href="change.jsp">Change data</a></li>
			<li><a href="add.jsp">Add data</a></li>
			<li><a href="import_export.jsp">Import/ Export data</a></li>
			<li><a href="settleInOut.jsp">Settle in/ settle out</a></li>
		</ul>
		<form action="LogOut">
			<input type="submit" value="Log Out">
		</form>
	</div>
	<table>
		<thead>
			<tr>
				<th>Number</th>
				<th>Content</th>
				<th>Stars</th>
				<th>Price</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="room" items="${list}">
				<tr>
					<td><c:out value="${room.getNumber()}" /></td>
					<td><c:out value="${room.getContent()}" /></td>
					<td><c:out value="${room.getStars()}" /></td>
					<td><c:out value="${room.getPrice()}" /></td>
					<td><c:out value="${room.getStatus()}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="view.jsp">Back</a>
</body>
</html>