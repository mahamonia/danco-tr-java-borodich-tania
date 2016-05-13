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
<p>
	<div class="menu">
		<ul>
			<li><a href="view/View.jsp">View data</a></li>		
			<li><a href="change/Change.jsp">Change data</a></li>
			<li><a href="add/Add.jsp">Add data</a></li>
			<li>Settle in/ settle out</a></li>				
		</ul>
	</div>
<form method="post" action="../SettleInOut">
			Input id guest:<br> <input type="text" size="40" name="id"><br>
		<input type="submit" value="Next">
	</form>
</body>
</html>