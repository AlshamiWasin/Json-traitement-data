package DAL;

import BO.Acteur;
import BO.Film;
import BO.Genre;
import BO.Pays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FilmDAO {

    private EntityManager em;

    public FilmDAO(EntityManager em) {
        this.em = em;
    }

    public void createFilm(Film film){
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }

    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.id = :id", Film.class);
        query.setParameter("id", film.getId());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
