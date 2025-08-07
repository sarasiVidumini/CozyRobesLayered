package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(CustomerDTO customerDto) throws SQLException;
    public ArrayList<CustomerDTO> getAll() throws SQLException;
    public boolean update(CustomerDTO customerDto) throws SQLException;
    public boolean delete(String customerId) throws SQLException;
    public ArrayList<CustomerDTO> search(String search) throws SQLException;
    public ArrayList<String> getAllCustomerIds() throws SQLException;
    public String getCustomerIdByContact(String contact) throws SQLException;
    public String getCustomerNameById(String customerId) throws SQLException;
}
