package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public Film getFilmById(int filmId) {
//		  Implement the findFilmById method that takes an int film ID, 
//		  and returns a Film object (or null, if the film ID returns no data.)
		Film film = null;

//			SQL query in film table
		String sql;
		sql = "SELECT * FROM film WHERE id = ?";

//			Connects to database
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);

//			    Sets variable for the bind 
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			

//				If found, makes new film object and returns it
				if (rs.next()) {
					film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
							rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
							rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
							rs.getString("rating"), rs.getString("special_features"));

//					set list of actors from film
//					List<Actor> actors = findActorsByFilmId(filmId);
//					film.setActors(actors);
//				}
					rs.close();
					stmt.close();
					conn.close();

				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return film;

		

	}
}
