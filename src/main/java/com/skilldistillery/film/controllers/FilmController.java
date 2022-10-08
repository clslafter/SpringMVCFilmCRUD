package com.skilldistillery.film.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	
	@Autowired
	private FilmDAO filmDao;
	
	
	
	@RequestMapping (path = "showFilm.do", method = RequestMethod.GET, params = "filmId")
	public ModelAndView showFilm (Integer filmId) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(filmId);
		mv.addObject("film", film);
		mv.setViewName("film");
		return mv;
		
	}

}
