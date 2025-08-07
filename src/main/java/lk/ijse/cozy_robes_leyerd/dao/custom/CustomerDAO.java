package lk.ijse.cozy_robes_leyerd.dao.custom;

import lk.ijse.cozy_robes_leyerd.dao.CrudDAO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    public ArrayList<String> getAllCustomerIds() throws SQLException;
    public String getCustomerIdByContact(String contact) throws SQLException;
    public String getCustomerNameById(String customerId) throws SQLException;
}
