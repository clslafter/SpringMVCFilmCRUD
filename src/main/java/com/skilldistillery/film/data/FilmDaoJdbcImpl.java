package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDaoJdbcImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	String user = "student";
	String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found");
			e.printStackTrace();

		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();

		while (filmResult.next()) {
			film = new Film(filmResult.getInt("id"), filmResult.getString("title"), filmResult.getString("description"),
					filmResult.getInt("release_year"), filmResult.getInt("language_id"),
					filmResult.getInt("rental_duration"), filmResult.getDouble("rental_rate"),
					filmResult.getInt("length"), filmResult.getDouble("replacement_cost"),
					filmResult.getString("rating"), filmResult.getString("special_features"));
			film.setActors(findActorsByFilmId(filmResult.getInt("id")));
			film.setLanguage(getLanguageById(filmResult.getInt("id")));
			film.setCategories(findCategoriesById(filmResult.getInt("id")));
		}

		filmResult.close();
		stmt.close();
		conn.close();
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();

		while (actorResult.next()) {
			actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
					actorResult.getString("last_name"));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actorList = new ArrayList<>();
		Actor newActor;
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT actor.id, actor.first_name, actor.last_name, film.title       \n"
				+ "      FROM actor JOIN film_actor fa ON actor.id = fa.actor_id           \n"
				+ "                 JOIN film ON fa.film_id = film.id                      \n"
				+ "                 WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet actorFilmResult = stmt.executeQuery();

		while (actorFilmResult.next()) {
			newActor = new Actor(actorFilmResult.getInt("id"), actorFilmResult.getString("first_name"),
					actorFilmResult.getString("last_name"));
			actorList.add(newActor);
		}

		actorFilmResult.close();
		stmt.close();
		conn.close();
		return actorList;
	}

	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();
		Film film = null;
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet filmResult = stmt.executeQuery();

		while (filmResult.next()) {
			film = new Film(filmResult.getInt("id"), filmResult.getString("title"), filmResult.getString("description"),
					filmResult.getInt("release_year"), filmResult.getInt("language_id"),
					filmResult.getInt("rental_duration"), filmResult.getDouble("rental_rate"),
					filmResult.getInt("length"), filmResult.getDouble("replacement_cost"),
					filmResult.getString("rating"), filmResult.getString("special_features"));
			film.setActors(findActorsByFilmId(filmResult.getInt("id")));
			film.setLanguage(getLanguageById(filmResult.getInt("id")));
			films.add(film);
		}

		return films;
	}

	public String getLanguageById(int filmId) throws SQLException {
		String language = null;
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT name \"language\" FROM film               \n"
				+ "    JOIN language ON film.language_id = language.id \n" + "    WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet langResult = stmt.executeQuery();

		while (langResult.next()) {
			language = new String(langResult.getString("language"));
		}
		langResult.close();
		stmt.close();
		conn.close();
		return language;
	}

	@Override
	public String getCategoryById(int filmId) throws SQLException {
		String category = null;
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT name \"category\"       \n"
				+ "      FROM category JOIN film_category fc ON category.id = fc.category_id           \n"
				+ "                 JOIN film ON fc.film_id = film.id                      \n"
				+ "                 WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet categoryResult = stmt.executeQuery();

		while (categoryResult.next()) {
			category = new String(categoryResult.getString("category"));
		}
		categoryResult.close();
		stmt.close();
		conn.close();
		return category;
	}
	
	@Override
	public List <String> findCategoriesById(int filmId) throws SQLException{
		List <String> categoryList = new ArrayList<> ();
		
		String user = "student";
		String pass = "student";
		
		String newCategory;

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT name \"category\"       \n"
				+ "      FROM category JOIN film_category fc ON category.id = fc.category_id           \n"
				+ "                 JOIN film ON fc.film_id = film.id                      \n"
				+ "                 WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet categoryResult = stmt.executeQuery();
		
		while (categoryResult.next()) {
			newCategory = categoryResult.getString("category");
		}

		categoryResult.close();
		stmt.close();
		conn.close();
		return categoryList;
		
		
	}

	@Override
	public Film createFilm(Film film) {
		String user = "student";
		String pass = "student";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO film (title,  description, release_year,  language_id, rental_duration"
					+ "rental_rate, length, replacement_cost, rating ) VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);

				}
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);
		}

		return film;
	}

	@Override
	public boolean deleteFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Film updateFilm(Film film) {
		String user = "student";
		String pass = "student";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, "
					+ "language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, "
					+ "replacement_cost = ?, rating = ? WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setInt(10, film.getId());

			int updateCount = stmt.executeUpdate();

			 
				ResultSet keys = stmt.getGeneratedKeys();
				while (keys.next()) {
					System.out.println("Updated film's Id:" + keys.getInt(1));	
					
				}
			
			
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			film = null;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error updating film " + film);
		}

		return film;
	}
}
