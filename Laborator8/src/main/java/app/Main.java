package app;

import app.city.City;
import app.country.CountryDAO;
import app.database.*;
import app.city.CityDAO;
import app.continent.ContinentDAO;
import app.map.MainFrame;

public class Main {

    public static void main(String[] args) {
        Main lab8 = new Main();
        lab8.homework();
    }

    public void homework(){
        try {
            CityDAO cityDAO = new CityDAO();
            City cityOne = (City) cityDAO.findById(10);
            City cityTwo = (City) cityDAO.findById(100);
            System.out.println("Distance from " + cityOne.getName() + " to " + cityTwo.getName() + " is " + cityOne.distanceBetween(cityTwo));
            new MainFrame().setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    public void compulsory(){
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");
            Database.getConnection().commit();
            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            countries.create("Romania", "1", europeId);
            countries.create("Ukraine","2",  europeId);
            Database.getConnection().commit();
            var cities = new CityDAO();
            cities.create("asd",1,1,1,1);
            Database.getConnection().commit();
            try (Statement stmt = Database.getConnection().createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "select * from continents")) {

                System.out.println("Continents: ");
                while(rs.next()){
                    System.out.println(rs.getInt("id") + " " + rs.getString("name"));
                }
            }catch (SQLException e) {
                System.err.println(e);
            }

            try (Statement stmt = Database.getConnection().createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "select id,name,continent from countries")) {

                System.out.println("Countries: ");
                while(rs.next()){
                    System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("continent"));
                }
            }catch (SQLException e) {
                System.err.println(e);
            }

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
     */

}
