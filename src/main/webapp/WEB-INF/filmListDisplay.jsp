<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film List Display</title>
</head>
<body>
	<h2>List of films by keyword</h2>
	<table>
		<c:forEach var="film" items="${filmList }">
		<tr>
		<td>${film.id }</td>
		<td>${film.title }</td>
		</tr>
		</c:forEach>
</table>
</body>
</html>

