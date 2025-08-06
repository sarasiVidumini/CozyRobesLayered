package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.CustomerBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.CustomerDAO;
import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String getNextId() throws SQLException {
       return customerDAO.getNextId();
    }

    @Override
    public boolean save(CustomerDTO customerDto) throws SQLException {
       return customerDAO.save(new Customer(customerDto.getCustomerId(), customerDto.getName(), customerDto.getPhone(), customerDto.getEmail()));
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getPhone(), customer.getEmail()));
        }
        return customerDTOS;
    }

    @Override
    public boolean update(CustomerDTO customerDto) throws SQLException {
      return customerDAO.update(new Customer(customerDto.getCustomerId(), customerDto.getName(), customerDto.getPhone(), customerDto.getEmail()));

    }

    @Override
    public boolean delete(String customerId) throws SQLException {
       return customerDAO.delete(customerId);
    }

    @Override
    public ArrayList<CustomerDTO> search(String search) throws SQLException {
        ArrayList<Customer> customers = customerDAO.search(search);

        ArrayList<CustomerDTO> dtos = new ArrayList<>();
        for (Customer c : customers) {
            dtos.add(new CustomerDTO(c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail()));
        }
        return dtos;
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException {
        return customerDAO.getAllCustomerIds();
    }


    public String getCustomerIdByContact(String contact) throws SQLException {
        return customerDAO.getCustomerIdByContact(contact);
    }


    public String getCustomerNameById(String customerId) throws SQLException {
        return customerDAO.getCustomerNameById(customerId);
    }

}
