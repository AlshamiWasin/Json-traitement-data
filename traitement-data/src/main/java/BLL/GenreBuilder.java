package BLL;

import BO.Genre;
import DAL.FilmDAO;
import DAL.GenreDAO;

import javax.persistence.EntityManager;

public class GenreBuilder {

    private static GenreDAO implGenre;

    public GenreBuilder(EntityManager em) {
        implGenre = new GenreDAO(em);
    }

    public static Genre builderGenre(String g) {
        Genre genre = new Genre();
        genre.setNom(g);
        return getGenre(genre);
    }

    public static Genre getGenre(Genre genreVerif) {
        if(implGenre.getGenre(genreVerif) == null) {
            implGenre.createGenre(genreVerif);
            return genreVerif;
        } else {
            return implGenre.getGenre(genreVerif);
        }
    }

}
