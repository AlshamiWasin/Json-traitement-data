package DAL;

import BO.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GenreDAO {

    private EntityManager em;

    public GenreDAO(EntityManager em) {
        this.em = em;
    }

    public void createGenre(Genre genre){
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }

    public Genre getGenre(Genre genre) {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.nom = :genre", Genre.class);
        query.setParameter("genre", genre.getNom());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
