package DAL;

import BO.Realisateur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RealisateurDAO {

    private EntityManager em;

    public RealisateurDAO(EntityManager em) {
        this.em = em;
    }

    public void createRealisateur(Realisateur realisateur){
        em.getTransaction().begin();
        em.persist(realisateur);
        em.getTransaction().commit();
    }
    public Realisateur getRealisateur(Realisateur realisateur) {
        TypedQuery<Realisateur> query = em.createQuery("SELECT r FROM Realisateur r WHERE r.infoPersonnel.identite = :identite", Realisateur.class);
        query.setParameter("identite", realisateur.getInfoPersonnel().getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
