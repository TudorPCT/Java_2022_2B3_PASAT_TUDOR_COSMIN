package app.continent;

import app.database.Database;
import app.exceptions.ContinentNotFoundException;
import app.exceptions.CountryNotFoundException;
import app.generic.GenericDAO;
import app.generic.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO implements GenericDAO {
    public void create(Location o) throws SQLException {
        Continent continent = (Continent) o;
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into continents (name) values (?)")) {
            pstmt.setString(1, continent.getName());
            pstmt.executeUpdate();
        }
    }

    public Location findByName(String name) throws SQLException, ContinentNotFoundException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
            "select * from continents where name='" + name + "'")) {

            if (rs.next())
                return new Continent(
                        rs.getInt(1),
                        rs.getString(2));
            else
                throw new ContinentNotFoundException(name + " couldn't be found");
        }
    }

    public Location findById(Integer id) throws SQLException, ContinentNotFoundException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from continents where id='" + id + "'")) {

            if (rs.next())
                return new Continent(
                                rs.getInt(1),
                                rs.getString(2));
            else
                throw new ContinentNotFoundException(id + " couldn't be found");
        }
    }

    public List<Location> findAll() throws SQLException, CountryNotFoundException {
        Connection con = Database.getConnection();
        List<Location> result = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(
                "select * from continents")) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                do{
                    result.add(new Continent(
                            rs.getInt(1),
                            rs.getString(2)));
                }while (rs.next());
            }
            else
                throw new CountryNotFoundException("We couldn't find any continents in the database");
            return result;
        }
    }
}