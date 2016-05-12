<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Administrator</title>
<link rel="stylesheet" href="theme/style.css" type="text/css">
</head>
<h3>View service</h3>
	   <div class="menu">
		<ul>
			<li>View data</li>			
			<li><a href="change/Change.jsp">Change data</a></li>
			<li><a href="add/Add.jsp">Add data</a></li>
			<li><a href="settle/SettleInOut.jsp">Settle in/ settle out</a></li>
				
		</ul>
	</div>
	
	<form method="post" action="ViewService">
   <fieldset class = "text">
   <legend>Select the type of sorting </legend>
    <input type="radio" name="sort" value="price">price <br>
    <input type="radio" name="sort" value="name"> name<br>
      <p><input type="submit" value="View"></p>
  </fieldset>
 
</form>
</body>
</html>