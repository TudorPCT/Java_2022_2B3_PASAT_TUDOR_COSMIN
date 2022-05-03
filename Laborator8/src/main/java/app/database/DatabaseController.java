package app.database;

import app.city.City;
import app.city.CityDAO;
import app.continent.Continent;
import app.continent.ContinentDAO;
import app.country.Country;
import app.country.CountryDAO;
import app.exceptions.CityNotFoundException;
import app.exceptions.ContinentNotFoundException;
import app.exceptions.CountryNotFoundException;
import app.exceptions.IncorrectInputFileException;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class DatabaseController {

    private static ContinentDAO continentDAO = new ContinentDAO();
    private static CountryDAO countryDAO = new CountryDAO();
    private static CityDAO cityDAO = new CityDAO();

    public static void init(){
        ScriptRunner sr = new ScriptRunner(Database.getConnection());

        try (Reader reader = new BufferedReader(new FileReader("./src/main/resources/createTables.sql"))){
            sr.runScript(reader);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void populateFromDataSet(String filename) throws IOException, SQLException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = " ";
        String[] tempArr;

        if ((line = br.readLine()) != null
                && !line.equals("CountryName,CapitalName,CapitalLatitude,CapitalLongitude,CountryCode,ContinentName")) {
            throw new IncorrectInputFileException("Expected a file with the header CountryName,CapitalName,CapitalLatitude,CapitalLongitude,CountryCode,ContinentName");
        }
        while ((line = br.readLine()) != null) {
            tempArr = line.split(",");
            if (tempArr.length != 6)
                continue;
            insertIntoDB(tempArr);
            System.out.println();
        }
        Database.getConnection().commit();
        br.close();
    }

    private static void insertIntoDB(String[] tempArr) throws SQLException{
        try {
            continentDAO.findByName(tempArr[5]);
        }catch (ContinentNotFoundException e){
            continentDAO.create(new Continent(tempArr[5]));
        }

        try{
            countryDAO.findByName(tempArr[0]);
        }catch (CountryNotFoundException e){
            countryDAO.create(new Country(
                        tempArr[0],
                        tempArr[4],
                    ((Continent) continentDAO.findByName(tempArr[5])).getId()));
        }

        if(!tempArr[1].equals("N/A")) {
            try{
                cityDAO.findByName(tempArr[1]);
            }catch (CityNotFoundException e){
                cityDAO.create(new City(
                        tempArr[1],
                        ((Country) countryDAO.findByName(tempArr[0])).getId(),
                        1,
                        Double.parseDouble(tempArr[2]),
                        Double.parseDouble(tempArr[3]))
                );
            }
        }
    }
}
