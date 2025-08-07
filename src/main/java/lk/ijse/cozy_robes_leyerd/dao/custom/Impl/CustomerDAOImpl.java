package lk.ijse.cozy_robes_leyerd.dao.custom.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.CustomerDAO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;
import lk.ijse.cozy_robes_leyerd.controller.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select customer_id from customer order by customer_id desc  limit 1");
        char tableCharacter = 'C';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }
        return tableCharacter + "001";
    }

    public boolean save(Customer customerDto) throws SQLException {
        return SQLUtil.execute(
                "insert into customer values (?,?,?,?)",
                customerDto.getCustomerId(),
                customerDto.getName(),
                customerDto.getPhone(),
                customerDto.getEmail()
        );
    }

    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(new Customer(resultSet.getString("customer_id") , resultSet.getString("name") , resultSet.getString("phone") , resultSet.getString("email")));
        }
        return customers;
    }

    public boolean update(Customer customerDto) throws SQLException {
        return SQLUtil.execute(
                "update customer set name=? , phone =? , email=? where customer_id= ?",
                customerDto.getName(),
                customerDto.getPhone(),
                customerDto.getEmail(),
                customerDto.getCustomerId()
        );

    }

    public boolean delete(String customerId) throws SQLException {
        return SQLUtil.execute(
                "delete from customer where customer_id = ?",
                customerId
        );
    }

    public ArrayList<Customer> search(String search) throws SQLException {
        ArrayList<Customer> dtos = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE customer_id LIKE ? OR name LIKE ? OR phone LIKE ? OR email LIKE ?";
        String pattern = "%" + search + "%";

        ResultSet resultSet = SQLUtil.execute(sql, pattern, pattern, pattern, pattern);
        while (resultSet.next()) {
         dtos.add(new Customer(resultSet.getString("customer_id"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("email")));
        }
        return dtos;
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select customer_id from customer");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            list.add(id);
        }
        return list;
    }


    public String getCustomerIdByContact(String contact) throws SQLException {
        try {
            ResultSet resultSet = SQLUtil.execute("select customer_id from customer where phone = ?", contact);
            if (resultSet.next()) {
                return resultSet.getString("customer_id");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "Customer Not found";
    }


    public String getCustomerNameById(String customerId) throws SQLException {
        try {
            ResultSet resultSet = SQLUtil.execute("select name from customer where customer_id = ?", customerId);
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "Customer Not found";
    }

}
