package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.SupplierDTO;
import lk.ijse.cozy_robes_leyerd.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(SupplierDTO supplierDto) throws SQLException;
    public ArrayList<SupplierDTO> getAll() throws SQLException;
    public boolean update(SupplierDTO supplierDto) throws SQLException;
    public boolean delete(String supplier_id) throws SQLException;
    public ArrayList<SupplierDTO> search(String search) throws SQLException;

}
