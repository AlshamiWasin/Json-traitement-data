package BLL;

import BO.Realisateur;
import BO.Role;
import DAL.ActeurDAO;
import DAL.RealisateurDAO;
import DAL.RoleDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;

public class RoleBuilder {
    private static FilmBuilder filmBuilder;
    private static RoleDAO implRole;

    public RoleBuilder(EntityManager em) {
        filmBuilder = new FilmBuilder(em);
        implRole = new RoleDAO(em);
    }

    public Role builderRole(JSONObject r) {
        Role role = new Role();
        role.setCharacterName( (String) r.get("characterName") );
        role.setFilm(filmBuilder.builderFilm((JSONObject) r.get("film")));
        return getRole(role);
    }

    public static Role getRole(Role roleVerif) {
        if(implRole.getRole(roleVerif) == null) {
            implRole.createRole(roleVerif);
            return roleVerif;
        } else {
            return implRole.getRole(roleVerif);
        }
    }


}
