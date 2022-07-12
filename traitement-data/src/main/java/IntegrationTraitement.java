import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegrationTraitement {

    public static void main(String[] args) {


        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("D:\\Mohammas wasin\\Documents\\diginamic\\cours\\java17\\tp\\tp movie\\Projet_Internet_Movie_DataBase\\traitement-data\\src\\main\\resources\\resources\\films.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray acteurList = (JSONArray) obj;

            for (Object o : acteurList) {
                parseActeurObject( (JSONObject) o );
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    private static void parseActeurObject(JSONObject a)
    {

        //Acteur acteur = new Acteur();


        //acteur.setnom( (String) a.get("identite"));

        System.out.println("################");

        System.out.println((String) a.get("identite"));

        JSONObject naissance = (JSONObject) a.get("naissance");

        System.out.println(naissance.get("lieuNaissance"));
        System.out.println(naissance.get("dateNaissance"));

        System.out.println("################");

    }

}
