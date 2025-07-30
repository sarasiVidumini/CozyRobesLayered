package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.CustomerDAO;
import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

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
            Customer dto = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            dtos.add(dto);
        }
        return dtos;
    }



}
