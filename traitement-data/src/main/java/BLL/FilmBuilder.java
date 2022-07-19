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

public class FilmBuilder {

    private static ActeurDAO implActeur;
    private static FilmDAO implFilm;

    private static PaysBuilder paysBuilder;
    private static GenreBuilder genreBuilder;
    private static RealisateurBuilder realisateurBuilder;
    private static LieuTournageBuilder lieuTournageBuilder;

    public FilmBuilder(EntityManager em) {
        implFilm = new FilmDAO(em);
        paysBuilder = new PaysBuilder(em);
        genreBuilder = new GenreBuilder(em);
        realisateurBuilder = new RealisateurBuilder(em);
        lieuTournageBuilder = new LieuTournageBuilder(em);
        implActeur = new ActeurDAO(em);
    }

    public static Film builderFilm(JSONObject f) {
        Film film = new Film();

        film.setId((String) f.get("id"));
        if (f.get("pays") != null){
            film.setPays(paysBuilder.builderPays((JSONObject) f.get("pays")));
        }

        film.setNom((String) f.get("nom"));
        film.setUrl((String) f.get("url"));
        film.setPlot((String) f.get("plot"));
        film.setLangue((String) f.get("langue"));



        if (f.get("lieuTournage") != null){
            film.setLieuTournage(lieuTournageBuilder.builderLieuTournage((JSONObject) f.get("lieuTournage")));
        }

        /*List<Object> realisateurListJSON = (JSONArray) f.get("realisateurs");*/


        JSONArray realisateursJson = (JSONArray) f.get("realisateurs");
        List<Realisateur> realisateurList = new ArrayList<>();

        for (Object o : realisateursJson) {
            realisateurList.add(realisateurBuilder.builderRealisateur((JSONObject) o  ));
        }

        film.setRealisateurs(realisateurList);




        if (f.get("anneeSortie") != ""){

            String anneeSortie = (String) f.get("anneeSortie");

            if (anneeSortie.contains("-")){
                String[] lesAnnees = anneeSortie.split("-");
                film.setAnnesSortie(Integer.parseInt(lesAnnees[1]));
            }else {
                film.setAnnesSortie(Integer.parseInt((String) f.get("anneeSortie")));
            }


        }
        JSONArray genres = (JSONArray) f.get("genres");
        List<Genre> genreList= new ArrayList<>();

        if (genres != null){
            for (Object genre : genres) {

                genreList.add(genreBuilder.builderGenre((String) genre));
            }
        }

        for (Genre genre : genreList) {
            genre.getFilms().add(film);
        }

        film.setGenres(genreList);


        List<Acteur> acteurList = new ArrayList<>();
        JSONArray acteurJSON = (JSONArray) f.get("acteurs");
        for (Object o : acteurJSON) {
            acteurList.add(makeActuerPourFilm((JSONObject) o));
        }

        List<Acteur> castingPrincipalList = new ArrayList<>();
        JSONArray castingPrincipalJSON = (JSONArray) f.get("castingPrincipal");
        for (Object o : castingPrincipalJSON) {
            castingPrincipalList.add(makeActuerPrincibalPourFilm((JSONObject) o));
        }


        for (Acteur acteur : acteurList) {
            acteur.getFilms().add(film);
        }

        for (Acteur acteur : castingPrincipalList) {
            acteur.getFilmActeursPrincipal().add(film);
        }

        film.getActeurs().addAll(acteurList);
        film.getCastingPrincipal().addAll(castingPrincipalList);

        return getFilm(film);

    }

    public static Film getFilm(Film filmVerif) {
        if(implFilm.getFilm(filmVerif) == null) {
            implFilm.createFilm(filmVerif);
            return filmVerif;
        } else {
            return implFilm.getFilm(filmVerif);
        }
    }

    public static Acteur makeActuerPourFilm(JSONObject a) {

        Acteur acteur = new Acteur();

        acteur.setId((String) a.get("id"));

        InfoPersonnel infoPersonnel = new InfoPersonnel();
        infoPersonnel.setIdentite((String) a.get("identite"));
        infoPersonnel.setUrl((String) a.get("url"));

        acteur.setInfoPersonnel(infoPersonnel);

        JSONObject naissance = (JSONObject) a.get("naissance");

        if (naissance != null){

            if (naissance.get("dateNaissance") != ""){
                String[] dateNaissance = ((String) naissance.get("dateNaissance")).split("-");

                int year = Integer.parseInt(dateNaissance[0]);
                int month = Integer.parseInt(dateNaissance[1]);
                int day = Integer.parseInt(dateNaissance[2]);

                if (month == 0){
                    month = 1;
                }

                if (day == 0){
                    day = 1;
                }

                acteur.setDateNaissance(LocalDate.of(year , month, day));
            }
            if (naissance.get("lieuNaissance") != ""){
                acteur.setLieuNaissaice( (String) naissance.get("lieuNaissance"));
            }

        }
        return getActeur(acteur);
    }
    public static Acteur makeActuerPrincibalPourFilm(JSONObject a) {

        Acteur acteur = new Acteur();

        acteur.setId((String) a.get("id"));

        InfoPersonnel infoPersonnel = new InfoPersonnel();
        infoPersonnel.setIdentite((String) a.get("identite"));
        infoPersonnel.setUrl((String) a.get("url"));


        acteur.setInfoPersonnel(infoPersonnel);

        JSONObject naissance = (JSONObject) a.get("naissance");

        if (naissance != null){

            if (naissance.get("dateNaissance") != ""){
                String[] dateNaissance = ((String) naissance.get("dateNaissance")).split("-");

                int year = Integer.parseInt(dateNaissance[0]);
                int month = Integer.parseInt(dateNaissance[1]);
                int day = Integer.parseInt(dateNaissance[2]);

                if (month == 0){
                    month = 1;
                }

                if (day == 0){
                    day = 1;
                }

                acteur.setDateNaissance(LocalDate.of(year , month, day));
            }

            if (naissance.get("lieuNaissance") != ""){
                acteur.setLieuNaissaice( (String) naissance.get("lieuNaissance"));
            }

        }

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
