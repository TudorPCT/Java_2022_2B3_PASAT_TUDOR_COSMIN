package app;

import app.database.ContinentDAO;
import app.database.CountryDAO;
import app.database.Database;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String args[]) {

        ScriptRunner sr = new ScriptRunner(Database.getConnection());


        try (Reader reader = new BufferedReader(new FileReader("./src/main/resources/createTables.sql"))){
            sr.runScript(reader);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        try {
            var continents = new ContinentDAO();
            continents.create("Europe");
            Database.getConnection().commit();
            var countries = new CountryDAO();
            int europeId = continents.findByName("America");
            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);
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

}
