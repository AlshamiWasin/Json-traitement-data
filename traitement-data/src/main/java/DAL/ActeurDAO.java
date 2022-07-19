package DAL;

import BO.Acteur;
import BO.Film;
import BO.Pays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ActeurDAO {

    private EntityManager em;

    public ActeurDAO(EntityManager em) {
        this.em = em;
    }

    public void createActeur(Acteur acteur){
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }
    public Acteur getFilm(Acteur acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.infoPersonnel.identite = :identite", Acteur.class);
        query.setParameter("identite", acteur.getInfoPersonnel().getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }


    List<Acteur> acteurList = new ArrayList<>();





}
