package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(String title, 
			String description, 
			int releaseYear,
			int languageId, 
			int rentalDuration, 
			double rentalRate, 
			int length, 
			double replacementCost, 
			String rating, 
			String specialFeatures, 
			RedirectAttributes redir) throws SQLException {
		Film film = new Film();
		film.setTitle(title);
		film.setDescription(description);
		film.setReleaseYear(releaseYear);
		film.setLanguageId(languageId);
		film.setRentalDuration(rentalDuration);
		film.setRentalRate(rentalRate);
		film.setLength(length);
		film.setReplacementCost(replacementCost);
		film.setRating(rating);
		film.setSpecialFeatures(specialFeatures);
		filmDao.createFilm(film);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmAdded.do");
		return mv;
	}

	@RequestMapping(path = "filmAdded.do", method = RequestMethod.GET)
	public ModelAndView redirectAdd(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmAddConfirmation");
		return mv;
	}
	
	
	@RequestMapping(path = "searchFilmId.do", method = RequestMethod.GET, params = "id")
	public ModelAndView searchFilmId(int id, RedirectAttributes redir) throws SQLException {
		Film film = filmDao.findFilmById(id);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmDisplay.do");
		return mv;
	}
	
	@RequestMapping(path = "filmDisplay.do", method = RequestMethod.GET)
	public ModelAndView redirectDisplay(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmDisplay");
		return mv;
	}
	
	@RequestMapping(path = "modifyOrDelete.do", method = RequestMethod.GET)
	public ModelAndView modify(int id) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(id);
		mv.addObject( film);
		mv.setViewName("modifyOrDelete");
	
		return mv;
	}
	
	
	@RequestMapping(path = "searchFilmKeyword.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView searchFilmKeyword(String keyword, RedirectAttributes redir) throws SQLException {
		List<Film> filmList = filmDao.findFilmsByKeyword(keyword);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("filmList", filmList);
		mv.setViewName("redirect:filmListDisplay.do");
		return mv;
	}
	
	@RequestMapping(path = "filmListDisplay.do", method = RequestMethod.GET)
	public ModelAndView redirectDisplayKey(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmListDisplay");
		return mv;
	}
//	@RequestMapping(path="")
	
	@RequestMapping(path = "modifyFilm.do", method = RequestMethod.POST)
	public ModelAndView modifyFilm(@RequestParam("id") int id, Film film, RedirectAttributes redir) throws SQLException {
		ModelAndView mv = new ModelAndView();
		System.out.println(film);
		film.setId(id);
		System.out.println(film.getId());
		
		Film updatedFilm = filmDao.updateFilm(film);
		
		redir.addFlashAttribute("film", updatedFilm);
		mv.setViewName("redirect:filmModifyConfirmation.do");
//		if (updatedFilm == null) {
//			updatedFilm = new Film();
//			updatedFilm.setId(-1);
//		}
		return mv;
	}
	
	@RequestMapping(path = "filmModifyConfirmation.do", method = RequestMethod.GET)
	public ModelAndView modifyConfirmRedirect(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmModifyConfirmation");
		return mv;
	}
	
	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST, params = "id")
	public ModelAndView deleteFilm(int id, RedirectAttributes redir) throws SQLException {
		Film film = filmDao.findFilmById(id);
		boolean deleteTest = filmDao.deleteFilm(film);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("deleteTest", deleteTest);
		mv.setViewName("redirect:filmDeleteConfirmation.do");
		return mv;
	}
	
	@RequestMapping(path = "filmDeleteConfirmation.do", method = RequestMethod.GET)
	public ModelAndView redirectDeleteConfirmation(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmDeleteConfirmation");
		return mv;
	}


}
