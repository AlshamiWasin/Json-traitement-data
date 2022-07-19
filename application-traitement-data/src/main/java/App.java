import BO.Acteur;
import BO.Film;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Console;
import java.util.*;

public class App {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdmovie");
    static EntityManager em = emf.createEntityManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int nombre=0;
        do{
            afficherMenu();
            nombre = scanner.nextInt();
            scanner.nextLine();
            utiliserMenu(nombre);
        }while ( nombre != 7 );
    }

    public static void afficherMenu(){
        System.out.println("1. Affichage de la filmographie d’un acteur donné.");
        System.out.println("2. Affichage du casting d’un film donné.");
        System.out.println("3. Affichage des films sortis entre 2 années données.");
        System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés.");
        System.out.println("5. Affichage des acteurs communs à 2 films donnés.");
        System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("7. Fin de l’application");
    }

    public static void utiliserMenu(int menuNumber){



        switch (menuNumber){
            case 1:
                System.out.println(" whats the acter name ? : ");
                scanner.nextLine();
                String acteurName = scanner.nextLine();
                Acteur acteur = afficherFilmographie(acteurName , em);

                int i = 0;
                for (Film film : acteur.getFilmActeursPrincipal()) {
                    i++;
                    System.out.println(i);
                }
                i = 0;
                for (Film film : acteur.getFilms()) {
                    i++;
                    System.out.println(i);
                }

                break;
            case 2:

                System.out.println(" whats the film name ? : ");
                scanner.nextLine();
                String filmName = scanner.nextLine();
                Film film = afficherCastingFilm(filmName , em);
                for (Acteur filmActeur : film.getActeurs()) {
                    System.out.println(filmActeur.getInfoPersonnel().getIdentite());
                }

                break;
            case 3:

                System.out.println("whats the first year");
                int firstYear =  scanner.nextInt();
                System.out.println("whats the second year");
                int secondtYear =  scanner.nextInt();
                List<Film> filmList = afficherListFilmEnter2Annees(firstYear , secondtYear , em);

                for (Film film1 : filmList) {
                    System.out.println(film1.getNom());
                }

                break;
            case 4:

                System.out.println(" whats the first acter name ? : ");
                String acteur1 = scanner.nextLine();
                System.out.println(acteur1);



                System.out.println(" whats the second acter name ? : ");
                String acteur2 = scanner.nextLine();
                System.out.println(acteur2);

                List<Film> listFilmCommuns = afficherFilmCommunsEnter2Acteur(acteur1 , acteur2 , em);

                for (Film film1 : listFilmCommuns) {
                    System.out.println(film1.getNom());
                }

                break;
            case 5:

                System.out.println(" whats the first film name ? : ");
                String film1 = scanner.nextLine();



                System.out.println(" whats the second film name ? : ");
                String film2 = scanner.nextLine();


                Collection<Acteur> listActeurCommuns =afficherActeursCommunsEnter2Film(film1,film2 ,em);

                for (Acteur listActeurCommun : listActeurCommuns) {
                    System.out.println(listActeurCommun.getInfoPersonnel().getIdentite());
                }

                break;
            case 6:

                System.out.println("whats the first year");
                int firstYearfilm =  scanner.nextInt();
                System.out.println("whats the second year");
                int secondtYearfilm =  scanner.nextInt();

                scanner.nextLine();

                System.out.println("whats the actuer name");
                String acteurfromfilm =  scanner.nextLine();
                Collection<Film> filmList1 = new ArrayList<>();

                if (firstYearfilm < secondtYearfilm){
                    filmList1 = afficherListFilmEnter2AnneesPlusActeur(firstYearfilm , secondtYearfilm ,acteurfromfilm, em);
                }else {
                    filmList1 = afficherListFilmEnter2AnneesPlusActeur(secondtYearfilm , firstYearfilm ,acteurfromfilm, em);
                }



                for (Film filmfromlist1 : filmList1) {
                    System.out.println(filmfromlist1.getNom());
                }


                break;
            case 7:
                System.out.println("merci d'avoir utilisé nos services");
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }
    }

    public static Acteur afficherFilmographie(String acteurName , EntityManager em){

        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.infoPersonnel.identite = :identite", Acteur.class);
        query.setParameter("identite", acteurName);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;

    }

    public static Film afficherCastingFilm(String filmName , EntityManager em){

        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.nom = :nom", Film.class);
        query.setParameter("nom", filmName);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;

    }

    public static List<Film> afficherListFilmEnter2Annees(int annee1 , int annee2  , EntityManager em){

        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.annesSortie Between :annee1 and :annee2", Film.class);
        query.setParameter("annee1", annee1);
        query.setParameter("annee2", annee2);
        return query.getResultList().size() > 0 ? query.getResultList() : null;

    }

    public static List<Film> afficherFilmCommunsEnter2Acteur(String acteurName1,String acteurName2  , EntityManager em){



        TypedQuery<Acteur> query1 = em.createQuery("SELECT a FROM Acteur a WHERE a.infoPersonnel.identite = :acteurName1 ", Acteur.class);
        query1.setParameter("acteurName1", acteurName1);
        TypedQuery<Acteur> query2 = em.createQuery("SELECT a FROM Acteur a WHERE a.infoPersonnel.identite = :acteurName2 ", Acteur.class);
        query2.setParameter("acteurName2", acteurName2);
        List<Film> listCommuns = new ArrayList<>();
        if (query1.getResultList().size() > 0 && query2.getResultList().size() > 0 ){
            for (Film film : query1.getResultList().get(0).getFilms()) {
                if (query2.getResultList().get(0).getFilms().contains(film)){
                    listCommuns.add(film);
                }
            }

            return listCommuns;
        }else {
            return listCommuns;
        }
    }
    public static Collection<Acteur> afficherActeursCommunsEnter2Film(String filmName1, String filmName2  , EntityManager em){
        TypedQuery<Film> query1 = em.createQuery("SELECT f FROM Film f WHERE f.nom= :filmName1 ", Film.class);
        query1.setParameter("filmName1", filmName1);
        TypedQuery<Film> query2 = em.createQuery("SELECT f FROM Film f WHERE f.nom= :filmName2 ", Film.class);
        query2.setParameter("filmName2", filmName2);
        Set<Acteur> listCommuns = new HashSet<>();
        if (query1.getResultList().size() > 0 && query2.getResultList().size() > 0 ){
            for (Acteur acteur : query1.getResultList().get(0).getActeurs()) {
                if (query2.getResultList().get(0).getActeurs().contains(acteur)){
                    listCommuns.add(acteur);
                }
            }
            return listCommuns;
        }else {
            return listCommuns;
        }
    }

    public static Collection<Film> afficherListFilmEnter2AnneesPlusActeur(int annee1 , int annee2 , String acteurName , EntityManager em){

        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f join f.acteurs as a WHERE a.infoPersonnel.identite = :acteurName  and f.annesSortie Between :annee1 and :annee2 ", Film.class);
        query.setParameter("annee1", annee1);
        query.setParameter("annee2", annee2);
        query.setParameter("acteurName", acteurName);
        return query.getResultList().size() > 0 ? query.getResultList() : null;

    }




}