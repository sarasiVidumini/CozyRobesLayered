package lk.ijse.cozy_robes_leyerd.dao;

import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public String getNextId() throws SQLException;

    public boolean save(T customerDto) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public boolean update(T customerDto) throws SQLException;

    public boolean delete(String customerId) throws SQLException;

    public ArrayList<T> search(String search) throws SQLException;


}
