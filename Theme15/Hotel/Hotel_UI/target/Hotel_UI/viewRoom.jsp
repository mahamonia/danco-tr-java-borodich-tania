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
	<form method="GET" action="ViewRoom">
		<fieldset class="text">
			<legend>Select the type of sorting </legend>
			<input type="radio" name="sort" value="price">price <br>
			<input type="radio" name="sort" value="content"> content<br>
			<input type="radio" name="sort" value="stars"> stars<br>
		</fieldset>

		<fieldset class="text">
			<legend>Select the status of room </legend>
			<input type="radio" name="status" value="FREE">free <br>
			<input type="radio" name="status" value=""> all<br>
		</fieldset>
		<p>
			<input type="submit" value="View table" name ="view">
		</p>
		
		<br>
		<fieldset class="text">
			<legend> </legend>
			<input type="checkbox" name="freeRoom" >Show amount free room<br>
			<input type="submit" value="View amount" name ="view">
		</fieldset>		
	</form>

</body>
</html>