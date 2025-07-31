package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.EmployeeDTO;
import lk.ijse.cozy_robes_leyerd.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(EmployeeDTO employeeDto) throws SQLException;
    public boolean update(EmployeeDTO employeeDto) throws SQLException;
    public boolean delete(String employee_id) throws SQLException;
    public ArrayList<EmployeeDTO> search(String search) throws SQLException;
    public ArrayList<EmployeeDTO> getAll() throws SQLException;
}
