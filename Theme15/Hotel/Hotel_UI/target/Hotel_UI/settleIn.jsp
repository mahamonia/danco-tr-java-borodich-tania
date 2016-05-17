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
	<h3>Settle guest</h3>
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

	<form method="GET" action="SettleIn">

		<c:set var="idGuest" scope="application" value="${idGuest}" />
		Guest id = <input type="text" size="10" name="idGuest"
			value="<c:out value="${idGuest}" />"> <br> <br>

		Room id:<br> <select name="roomList">

			<c:forEach var="room" items="${list}">
				<c:set var="idRoom" scope="application" value="${room.getId()}" />
				<option value="idRoom"><c:out value="${room.getId()}" /></option>
				<br>
			</c:forEach>

		</select> <br> <br>

		<h5>Format date: yyyy-mm-dd</h5>
		Date in settle: <input type="text" size="10" name="dateInSettle"><br>
		Date out settle: <input type="text" size="10" name="dateOutSettle">

		<input type="submit" value="Settle">

	</form>
</body>
</html>