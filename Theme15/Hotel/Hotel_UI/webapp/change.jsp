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
<p>
	<h2>Hotel Administrator</h2>
	<h3>Change data</h3>
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
	<select onchange="location.href=this.value">
		<option selected  disabled>Select</option>
		<option value="http://localhost:8080/Hotel_UI/changeRoom.jsp">Room</option>
		<option value="http://localhost:8080/Hotel_UI/changeService.jsp">Service</option>
	</select>
</body>
</html>