<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="*" type="text/css">
<title>Table of available stock</title>
</head>
<body>
	<table>
		<thead>			
			<th>Title</th>
			<th>Quantity</th>
			<th>Author</th>
		</thead>
		
		<%
			String alma = "AlMa";
		%>
		
		<c:forEach var="stockData" items="${listToDisplay}">
			<c:out value="${stockData}"></c:out>
		</c:forEach>
				
	</table>
</body>
</html>