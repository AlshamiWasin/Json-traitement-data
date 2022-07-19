package BLL;

import BO.Acteur;
import BO.Film;
import BO.InfoPersonnel;
import BO.Role;
import DAL.ActeurDAO;
import DAL.FilmDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActeurBuilder {

    private static ActeurDAO implActeur;
    private static RoleBuilder roleBuilder;

    public ActeurBuilder(EntityManager em) {
        implActeur = new ActeurDAO(em);
        roleBuilder = new RoleBuilder(em);
    }


    public Acteur builderActeur(JSONObject a) {

        Acteur acteur = new Acteur();

        acteur.setId((String) a.get("id"));

        InfoPersonnel infoPersonnel = new InfoPersonnel();
        infoPersonnel.setIdentite((String) a.get("identite"));
        infoPersonnel.setUrl((String) a.get("url"));


        acteur.setInfoPersonnel(infoPersonnel);


        JSONObject naissance = (JSONObject) a.get("naissance");

        String[] dateNaissance = ((String) naissance.get("dateNaissance")).split("-");

        acteur.setDateNaissance(LocalDate.of(Integer.parseInt(dateNaissance[0]) , Integer.parseInt(dateNaissance[1]), Integer.parseInt(dateNaissance[2])));

        acteur.setLieuNaissaice( (String) naissance.get("lieuNaissance"));

        JSONArray roles = (JSONArray) a.get("roles");

        List<Role> roleList = new ArrayList<>();

        for (Object o : roles) {
            Role role = roleBuilder.builderRole((JSONObject) o);
            roleList.add(role);
        }

        for (Role role : roleList) {
            role.setActeur(acteur);
        }

        acteur.setRoles(roleList);


        return getActeur(acteur);
    }

    public static Acteur getActeur(Acteur acteurVerif) {
        if(implActeur.getFilm(acteurVerif) == null) {
            implActeur.createActeur(acteurVerif);
            return acteurVerif;
        } else {
            return implActeur.getFilm(acteurVerif);
        }
    }


}
