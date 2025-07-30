package lk.ijse.cozy_robes_leyerd.dao.cart;

import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public String getNextId() throws SQLException;

    public boolean save(T customerDto) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public boolean update(T customerDto) throws SQLException;

    public boolean delete(String customerId) throws SQLException;

    public ArrayList<CustomerDTO> search(String search) throws SQLException;


}
