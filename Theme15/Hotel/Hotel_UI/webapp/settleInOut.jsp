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
	<form method="GET" action="SettleInOut">
		Input id guest:<br> <input type="text" size="10" name="id"><br>
		<input type="submit" value="Next">
	</form>
</body>
</html>