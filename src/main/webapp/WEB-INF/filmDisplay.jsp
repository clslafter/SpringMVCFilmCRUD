<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Display</title>
</head>
<body>
	<h1>Film search by ID result:</h1>
	<br> ID: ${film.id }
	<br> Title: ${film.title }
	<br> Description: ${film.description }
	<br> Release Year: ${film.releaseYear }
	<br> Language ID: ${film.languageId }
	<br> Rental Duration: ${film.rentalDuration }
	<br> Rental Rate: ${film.rentalRate }
	<br> Length: ${film.length }
	<br> Replacement Cost: ${film.replacementCost }
	<br> Rating: ${film.rating }
	<br> Special Features: ${film.specialFeatures } 
<%-- 	These need to be for each looped <br> Categories: ${film.category } --%>
	<%-- <br> List of Actors ${film.listOfActors } --%>
	<br><br><br>
	<form action="modifyOrDelete.do" method="POST">
		<input type="number" hidden="true" name="film" value="${film }" /> <input
			type="submit" value="Modify or Delete this film" />
	</form><br><br><br>
<a href="index.html">Homepage</a><br>
</body>
</html>