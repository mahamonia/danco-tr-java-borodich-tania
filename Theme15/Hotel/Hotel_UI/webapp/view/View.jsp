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
	   <div class="menu">
		<ul>
			<li><p>View data</p></li>			
			<li><a href="change/Change.jsp">Change data</a></li>
			<li><a href="add/Add.jsp">Add data</a></li>
			<li><a href="settle/SettleInOut.jsp">Settle in/ settle out</a></li>
				
		</ul>
	</div>
		
	<form action="View">
		<select name="combobox">
			<option value="1">Guest</option> 
			<option value="2">Room</option>
			<option value="3">Service</option>
		</select>
		<input type="submit" value="OK">
	</form>
</body>
</html>