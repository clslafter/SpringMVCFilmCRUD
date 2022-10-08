<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Add Confirmation</title>
</head>
<body>
	<h2>Film Add Confirmation</h2>

	  <c:choose>
		 <c:when test="${empty film}">
Your film could not be added. Please try again.
         </c:when>
	<c:otherwise>
${film.title } was added successfully.
</c:otherwise>
	  </c:choose>
	<br><br><br><br>
	<a href="index.html">Homepage</a><br>
	<a href="filmDetails.jsp">Film Details</a> <-- this part needs work -->
</body>
</html>

