package lk.ijse.cozy_robes_leyerd.dao;

import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public String getNextId() throws SQLException;

    public boolean save(T dto) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public boolean update(T dto) throws SQLException;

    public boolean delete(String id) throws SQLException;

    public ArrayList<T> search(String search) throws SQLException;


}
