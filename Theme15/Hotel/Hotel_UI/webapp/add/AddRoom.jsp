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
	<h3>Add new room</h3>
	<form method="post" action="AddRoom">
		Number:<br> <input type="text" size="10" name="number"><br>
		Content:<br> <input type="text" size="10" name ="content"><br>
		Status:<br> <input type="text" size="10" name ="status"><br> Stars:<br>
		<input type="text" size="10" name ="stars"><br> Price:<br> <input
			type="text" size="10" name ="price"><br>
		<br> <input type="submit" value="add"><br>
	</form>
	<a href="view/View.jsp">Back</a>

</body>
</html>