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
	<h3>Change room</h3>
	<form method="post" action="ChangeRoom">

		Id room:<br> <input type="text" size="10" name="id"><br>
		<fieldset class="text">
			<legend>Select the type change </legend>
			<input type="radio" name="change" value="price">price <br>
			<input type="radio" name="change" value="status"> status<br>
		</fieldset>
		Value:<br> <input type="text" size="10" name="value"><br>
		<br> <input type="submit" value="Change"><br>
	</form>
	<a href="view/View.jsp">Back</a>

</body>
</html>