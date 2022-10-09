<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Film Confirmation</title>
</head>
<body>
	<h2>Film Deletion Confirmation</h2>

	<c:choose>
		<c:when test="${deleteTest == true}">
<p>The selected film was deleted. </p>

<br>
<br>
<a href="index.html">Back to home</a>
		</c:when>
		<c:otherwise>
<p>The selected film was not deleted.
An error occurred, please try again.</p>
<br>
<br>
<a href="index.html">Back to home</a>
		</c:otherwise>
	</c:choose>
</body>
</html>