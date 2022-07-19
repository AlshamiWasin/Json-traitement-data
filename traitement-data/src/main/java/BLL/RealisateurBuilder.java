package BLL;

import BO.Genre;
import BO.InfoPersonnel;
import BO.Realisateur;
import DAL.FilmDAO;
import DAL.PaysDAO;
import DAL.RealisateurDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;

public class RealisateurBuilder {

    private static RealisateurDAO implRealisateur;

    public RealisateurBuilder(EntityManager em) {
        implRealisateur= new RealisateurDAO(em);
    }

    public static Realisateur builderRealisateur(JSONObject r) {

        Realisateur realisateur = new Realisateur();

        InfoPersonnel infoPersonnel = new InfoPersonnel();

        infoPersonnel.setIdentite((String) r.get("identite"));
        infoPersonnel.setUrl((String) r.get("url"));

        realisateur.setInfoPersonnel(infoPersonnel);
        return  getRealisateur(realisateur);
    }

    public static Realisateur getRealisateur(Realisateur realisateurVerif) {
        if(implRealisateur.getRealisateur(realisateurVerif) == null) {
            implRealisateur.createRealisateur(realisateurVerif);
            return realisateurVerif;
        } else {
            return implRealisateur.getRealisateur(realisateurVerif);
        }
    }


}
