package app.city;

import app.database.Database;
import app.exceptions.CityNotFoundException;
import app.generic.GenericDAO;
import app.generic.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements GenericDAO {
    public void create(Location o) throws SQLException {
        City city = (City) o;
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into cities (name, country, capital, latitude, longitude) values (?,?, ?, ?, ?)")) {
            pstmt.setString(1, city.getName());
            pstmt.setInt(2, city.getCountry());
            pstmt.setInt(3, city.getCapital());
            pstmt.setDouble(4, city.getLatitude());
            pstmt.setDouble(5, city.getLongitude());
            pstmt.executeUpdate();
        }
    }

    public Location findByName(String name) throws SQLException, CityNotFoundException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select * from cities where name=?")) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return new City(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6));
            }
            else
                throw new CityNotFoundException(name + " couldn't be found");
        }
    }

    public Location findById(Integer id) throws SQLException, CityNotFoundException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from cities where id='" + id + "'")) {

            if (rs.next())
                return new City(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6));
            else
                throw new CityNotFoundException(id + " couldn't be found");
        }
    }

    public List<Location> findAll() throws SQLException, CityNotFoundException {
        Connection con = Database.getConnection();
        List<Location> result = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(
                "select * from cities")) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                do{
                    result.add(new City(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getDouble(6)));
                }while (rs.next());
            }
            else
                throw new CityNotFoundException("We couldn't find any continents in the database");
            return result;
        }
    }

}
