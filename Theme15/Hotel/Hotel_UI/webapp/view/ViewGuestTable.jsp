<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Administrator</title>
<link rel="stylesheet" href="theme/style.css" type="text/css">
</head>
<body>
<h3>View guest</h3>
<table>
		<thead >
			<tr>
				<th>Id</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
	<c:forEach var="user" items="${list}">
	<tr>
		<td><c:out value="${user.getId()}" /></td>
		<td><c:out value="${user.getName()}" /></td>
		<td></td>
	</tr>
</c:forEach>
		</tbody>
	</table>
	
	<a href="view/View.jsp">Back</a>
</body>
</html>