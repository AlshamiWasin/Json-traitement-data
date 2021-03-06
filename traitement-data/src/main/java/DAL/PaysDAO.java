package DAL;

import BO.Genre;
import BO.Pays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PaysDAO {

    private EntityManager em;

    public PaysDAO(EntityManager em) {
        this.em = em;
    }

    public void createPays(Pays pays){
        em.getTransaction().begin();
        em.persist(pays);
        em.getTransaction().commit();
    }
    public Pays getPays(Pays pays) {
        TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.nom = :nom", Pays.class);
        query.setParameter("nom", pays.getNom());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
