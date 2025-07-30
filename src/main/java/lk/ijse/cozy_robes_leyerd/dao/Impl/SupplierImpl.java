package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dto.SupplierDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierImpl {
    public String getNextSupplierId() throws SQLException {
        ResultSet resultSet = (ResultSet) SQLUtil.execute("select supplier_id from supplier order by supplier_id desc limit 1");

        char prefix = 'S';

        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // e.g., "S017"
            String numberPart = lastId.substring(1); // remove 'S'
            int nextNumber = Integer.parseInt(numberPart) + 1;
            return String.format("%c%03d", prefix, nextNumber); // e.g., "S018"
        }

        return prefix + "001";
    }

    public boolean saveSupplier(SupplierDTO supplierDto) throws SQLException {
        return (Boolean) SQLUtil.execute(
                "INSERT INTO supplier VALUES (?, ?, ?, ?)",
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getAddress(),
                supplierDto.getContact()
        );
    }

    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException {
        ResultSet resultSet = (ResultSet) SQLUtil.execute("SELECT * FROM supplier");

        ArrayList<SupplierDTO> supplierDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            SupplierDTO supplierDto = new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            supplierDtoArrayList.add(supplierDto);
        }

        return supplierDtoArrayList;
    }

    public boolean updateSupplier(SupplierDTO supplierDto) throws SQLException {
        return (Boolean) SQLUtil.execute(
                "update supplier set name = ?, address = ?, contact = ? WHERE supplier_id = ?",
                supplierDto.getName(),
                supplierDto.getAddress(),
                supplierDto.getContact(),
                supplierDto.getSupplierId()
        );
    }

    public boolean deleteSupplier(String supplier_id) throws SQLException {
        return (Boolean) SQLUtil.execute(
                "DELETE FROM supplier WHERE supplier_id = ?",
                supplier_id
        );
    }

    public ArrayList<SupplierDTO> searchSupplier(String search) throws SQLException {
        ArrayList<SupplierDTO> dtos = new ArrayList<>();
        String sql = "SELECT * FROM supplier WHERE supplier_id LIKE ? OR name LIKE ? OR address LIKE ? OR contact LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = (ResultSet) SQLUtil.execute(sql, pattern, pattern, pattern, pattern);

        while (resultSet.next()) {
            SupplierDTO dto = new SupplierDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public ArrayList<String> getAllSuppliersIds() throws SQLException {
        ResultSet resultSet = (ResultSet) SQLUtil.execute("SELECT supplier_id FROM supplier");

        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            list.add(id);
        }

        return list;
    }
}
