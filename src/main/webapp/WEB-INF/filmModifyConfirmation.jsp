<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Edit Confirmation</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty film}">
An error occurred, try again. 
         </c:when>
		<c:otherwise>
Film ID: ${film.id }<br>
${film.title } was modified.<br><br>
<a href="index.html">Back to home</a>
</c:otherwise>
	</c:choose>

</body>
</html>