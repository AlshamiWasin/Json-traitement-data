package BLL;

import BO.*;
import DAL.ActeurDAO;
import DAL.FilmDAO;
import DAL.GenreDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActeurManager {

    private static volatile ActeurManager instance=null;
    private static ActeurDAO implActeur;
    private static ActeurBuilder acteurBuilder;

    public ActeurManager(EntityManager em) {
        implActeur = new ActeurDAO(em);
        acteurBuilder = new ActeurBuilder(em);
    }


    public void saveActeur(JSONObject a){

        Acteur acteur =  acteurBuilder.builderActeur(a);

    }








}
