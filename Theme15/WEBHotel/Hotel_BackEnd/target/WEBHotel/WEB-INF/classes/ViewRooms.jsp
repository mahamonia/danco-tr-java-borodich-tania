<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.danco.model.entity.Room"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
		<thead>
			<tr>
				<th>Id room</th>
				<th>Number room</th>
				<th>Price room</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Room> list = (List<Room>) request.getAttribute("results");
			Iterator iterator = list.iterator();
			System.out.print(list.size());
				int i = 0;
				while (iterator.hasNext()) {
					i++;
					Room room = (Room) iterator.next();
			%>
			<tr>
				<td><%=room.getId()%></td>
				<td><%=room.getNumber()%></td>
				<td><%=room.getPrice()%></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
	<p>
		<A href="index.html">Home</A>
	</p>
</body>
</html>