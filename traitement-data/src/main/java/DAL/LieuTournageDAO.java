package DAL;

import BO.Genre;
import BO.LieuTournage;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class LieuTournageDAO {

    private EntityManager em;

    public LieuTournageDAO(EntityManager em) {
        this.em = em;
    }

    public void createLieuTournage(LieuTournage lieuTournage){
        em.getTransaction().begin();
        em.persist(lieuTournage);
        em.getTransaction().commit();
    }

    public LieuTournage getLieuTournage(LieuTournage lieuTournage) {
        TypedQuery<LieuTournage> query = em.createQuery("SELECT l FROM LieuTournage l WHERE l.ville = :ville and l.pays = :pays and l.etatDept = :etatDept", LieuTournage.class);
        query.setParameter("ville", lieuTournage.getVille());
        query.setParameter("pays", lieuTournage.getPays());
        query.setParameter("etatDept", lieuTournage.getEtatDept());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
