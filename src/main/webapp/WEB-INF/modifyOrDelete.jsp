<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify or Delete</title>
</head>
<body>
<h2> Modify your film choice </h2>
    <br> Film ID: ${film.id } 
	<form  action="modifyFilm.do" method="POST">
	<br> Title:  <input type="text" name="title" value="${film.title }"/> 
	<br> Description: <input type="text" name="description" value="${film.description }"/> 
	<br> Release Year: <input type="text" name="releaseYear" value="${film.releaseYear }"/>
	<br> Language ID: <input type="text" name="languageId" value="${film.languageId }"/>
	<br> Rental Duration: <input type="text" name="rentalDuration" value="${film.rentalDuration }"/>
	<br> Rental Rate: $<input type="text" name="rentalRate" value="${film.rentalRate }"/>
	<br> Length: <input type="text" name="length" value="${film.length }"/>
	<br> Replacement Cost: $<input type="text" name="replacementCost" value="${film.replacementCost }"/>
	<br> Rating: <input type="text" name="rating" value="${film.rating }"/>
		<input type="hidden" name="id" value="${film.id }" />	
	<br> 
	
	<input type="submit" value="Submit" />
	
	</form><br><br>
	<h2>Delete this film?</h2><br>
	<form  action="deleteFilm.do" method="POST" >
	<input type="hidden" name="id" value="${film.id }" />	
	<input type="submit" value="Delete this film" />
	</form>
	

</body>
</html>