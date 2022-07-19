import BLL.ActeurManager;
import BO.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IntegrationTraitement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdmovie");
    static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {



        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("D:\\Mohammas wasin\\Documents\\diginamic\\cours\\java17\\tp\\tp movie\\Projet_Internet_Movie_DataBase\\traitement-data\\src\\main\\resources\\films.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray acteurList = (JSONArray) obj;

            ActeurManager acteurManager = new ActeurManager(em);

            for (Object o : acteurList) {
                acteurManager.saveActeur( (JSONObject) o );
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }




}





