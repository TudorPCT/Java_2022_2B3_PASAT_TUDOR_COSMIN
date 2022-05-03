package app.country;

import app.database.Database;
import app.exceptions.CountryNotFoundException;
import app.generic.GenericDAO;
import app.generic.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements GenericDAO {
    public void create(Location o) throws SQLException {
        Country country = (Country) o;
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name, code, continent) values (?,?,?)")) {
            pstmt.setString(1, country.getName());
            pstmt.setString(2, country.getCode());
            pstmt.setInt(3, country.getContinent());
            pstmt.executeUpdate();
        }
    }

    public Location findByName(String name) throws SQLException, CountryNotFoundException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement(
                "select * from countries where name=?")) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return new Country(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
            }
            else
                throw new CountryNotFoundException(name + " couldn't be found");
        }
    }

    public Location findById(Integer id) throws SQLException, CountryNotFoundException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from countries where id='" + id + "'")) {

            if (rs.next())
                return new Country(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
            else
                throw new CountryNotFoundException(id + " couldn't be found");
        }
    }

    public List<Location> findAll() throws SQLException, CountryNotFoundException {
        Connection con = Database.getConnection();
        List<Location> result = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(
                "select * from countries")) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                do{
                    result.add(new Country(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4)));
                }while (rs.next());
            }
            else
                throw new CountryNotFoundException("We couldn't find any city in the database");
            return result;
        }
    }

}
