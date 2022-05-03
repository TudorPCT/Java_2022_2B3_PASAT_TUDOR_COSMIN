package app.generic;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO {
    void create(Location o) throws SQLException;
    List<Location> findAll() throws SQLException, RuntimeException;
    Location findById(Integer id)  throws SQLException, RuntimeException;
    Location findByName(String name)  throws SQLException, RuntimeException;
}
