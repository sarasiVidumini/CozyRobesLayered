package lk.ijse.cozy_robes_leyerd.bo.custom.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.EmployeeBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.EmployeeDAO;
import lk.ijse.cozy_robes_leyerd.dto.EmployeeDTO;
import lk.ijse.cozy_robes_leyerd.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    private  EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public String getNextId() throws SQLException {
      return employeeDAO.getNextId();
    }


    public boolean save(EmployeeDTO employeeDto) throws SQLException {
       return employeeDAO.save(new Employee(employeeDto.getEmployeeId(),employeeDto.getUserId(),employeeDto.getName(),employeeDto.getRole(),employeeDto.getSalary()));
    }

    public boolean update(EmployeeDTO employeeDto) throws SQLException {
      return employeeDAO.update(new Employee(employeeDto.getEmployeeId(),employeeDto.getUserId(),employeeDto.getName(),employeeDto.getRole(),employeeDto.getSalary()));
    }

    public boolean delete(String employee_id) throws SQLException {
       return employeeDAO.delete(employee_id);
    }

    public ArrayList<EmployeeDTO> search(String search) throws SQLException {
        ArrayList<Employee> employees = employeeDAO.search(search);
        ArrayList<EmployeeDTO> dtos = new ArrayList<>();
       for (Employee employee : employees) {
           dtos.add(new EmployeeDTO(employee.getEmployeeId(),employee.getUserId(),employee.getName(),employee.getRole(),employee.getSalary()));
       }
       return dtos;
    }

    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDTO(employee.getEmployeeId(),employee.getUserId(),employee.getName(),employee.getRole(),employee.getSalary()));
        }

        return employeeDtos;
    }
}
