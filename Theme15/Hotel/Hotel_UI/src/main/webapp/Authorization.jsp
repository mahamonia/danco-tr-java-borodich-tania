<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="Authentication" > 
            <table> 
                <tr> 
                    <th align="right">Username:</th> 
                    <td align="left"><input type="text" name="username"></td> 
                </tr> 
                <tr> 
                    <th align="right">Password:</th> 
                    <td align="left"><input type="password" name="password"></td> 
                </tr> 
                <tr> 
                    <td align="right"><input type="submit" value="Log In"></td> 
                </tr> 
            </table> 
        </form>

</body>
</html>