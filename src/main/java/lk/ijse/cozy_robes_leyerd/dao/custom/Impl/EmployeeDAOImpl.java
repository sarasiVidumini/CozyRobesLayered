package lk.ijse.cozy_robes_leyerd.dao.custom.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.EmployeeDAO;
import lk.ijse.cozy_robes_leyerd.entity.Employee;
import lk.ijse.cozy_robes_leyerd.controller.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select employee_id from employee order by employee_id desc limit 1");
        char tableCharacter = 'E';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String latIdNumberString = lastId.substring(1);
            int latIdNumber = Integer.parseInt(latIdNumberString);
            int nextIdNumber = latIdNumber + 1;
            String nextIdString = String.format(tableCharacter +"%03d", nextIdNumber);
            return nextIdString;
        }

        return tableCharacter + "001";
    }


    public boolean save(Employee employeeDto) throws SQLException {
        return SQLUtil.execute(
                "insert into employee values(?,?,?,?,?)",
                employeeDto.getEmployeeId(),
                employeeDto.getUserId(),
                employeeDto.getName(),
                employeeDto.getRole(),
                employeeDto.getSalary()
        );
    }

    public boolean update(Employee employeeDto) throws SQLException {
        return SQLUtil.execute(
                "update employee set user_id = ? , name = ? , role = ? , salary = ? where employee_id = ?",
                employeeDto.getUserId(),
                employeeDto.getName(),
                employeeDto.getRole(),
                employeeDto.getSalary(),
                employeeDto.getEmployeeId()
        );
    }

    public boolean delete(String employee_id) throws SQLException {
        return SQLUtil.execute(
                "delete from employee where employee_id = ?",
                employee_id
        );
    }

    public ArrayList<Employee> search(String search) throws SQLException {
        ArrayList<Employee> dtos = new ArrayList<>();
        String sql = "select * from employee where employee_id LIKE ? OR user_id LIKE ? OR name LIKE ? OR role LIKE ? OR salary LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern , pattern, pattern, pattern , pattern);
        while (resultSet.next()) {
            dtos.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5)));
        }
        return dtos;
    }

    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from employee");
        ArrayList<Employee> employeeDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            employeeDtoArrayList.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5)));
        }

        return employeeDtoArrayList;
    }


}
