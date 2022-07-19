package BLL;

import BO.Genre;
import BO.LieuTournage;
import DAL.GenreDAO;
import DAL.LieuTournageDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;

public class LieuTournageBuilder {

    private static LieuTournageDAO implLieuTournage;

    public LieuTournageBuilder(EntityManager em) {
        implLieuTournage = new LieuTournageDAO(em);
    }

    public static LieuTournage builderLieuTournage(JSONObject l) {

        LieuTournage lieuTournage = new LieuTournage();

        lieuTournage.setVille( (String) l.get("ville"));
        lieuTournage.setEtatDept((String) l.get("etatDept"));
        lieuTournage.setPays((String) l.get("pays"));

        return getLieuTournage(lieuTournage);
    }

    public static LieuTournage getLieuTournage(LieuTournage lieuTournageVerif) {
        if(implLieuTournage.getLieuTournage(lieuTournageVerif) == null) {
            implLieuTournage.createLieuTournage(lieuTournageVerif);
            return lieuTournageVerif;
        } else {
            return implLieuTournage.getLieuTournage(lieuTournageVerif);
        }
    }


}
