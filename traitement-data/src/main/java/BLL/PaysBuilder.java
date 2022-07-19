package BLL;

import BO.Genre;
import BO.Pays;
import DAL.PaysDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;

public class PaysBuilder {

    private static PaysDAO implPays;

    public PaysBuilder(EntityManager em) {
        implPays= new PaysDAO(em);
    }

    public Pays builderPays(JSONObject p) {
        BO.Pays pays = new BO.Pays();
        pays.setNom((String) p.get("nom"));
        pays.setUrl((String) p.get("url"));
        return getPays(pays);
    }

    public static Pays getPays(Pays paysVerif) {
        if(implPays.getPays(paysVerif) == null) {
            implPays.createPays(paysVerif);
            return paysVerif;
        } else {
            return implPays.getPays(paysVerif);
        }
    }

}
