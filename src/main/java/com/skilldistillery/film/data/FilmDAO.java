package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {


	Film findFilmById(int filmId) throws SQLException;
	
	Actor findActorById(int actorId) throws SQLException;

	List<Actor> findActorsByFilmId(int filmId) throws SQLException;

	
	List <Film> findFilmsByKeyword(String key) throws SQLException;
	
	String getLanguageById(int filmId) throws SQLException;
	
	String getCategoryById(int filmId) throws SQLException;


	
	//full crud
	//public Actor createActor(Actor actor);
	
	Film createFilm(Film film);
	
	boolean deleteFilm(Film film);
	
	Film updateFilm(int filmId, Film obj);
}
