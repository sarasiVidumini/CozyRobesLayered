package lk.ijse.cozy_robes_leyerd.bo.custom.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.SupplierBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.SupplierDAO;
import lk.ijse.cozy_robes_leyerd.dto.SupplierDTO;
import lk.ijse.cozy_robes_leyerd.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    private SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public String getNextId() throws SQLException {
       return supplierDAO.getNextId();
    }

    public boolean save(SupplierDTO supplierDto) throws SQLException {
        return supplierDAO.save(new Supplier(supplierDto.getSupplierId(),supplierDto.getName(),supplierDto.getAddress(),supplierDto.getContact()));
    }

    public ArrayList<SupplierDTO> getAll() throws SQLException {
        ArrayList<Supplier>supplierList = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDtos = new ArrayList<>();
       for (Supplier supplier : supplierList) {
           supplierDtos.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getAddress(),supplier.getContact()));
       }
        return supplierDtos;
    }

    public boolean update(SupplierDTO supplierDto) throws SQLException {
        return supplierDAO.update(new Supplier(supplierDto.getSupplierId(),supplierDto.getName(),supplierDto.getAddress(),supplierDto.getContact()));
    }

    public boolean delete(String supplier_id) throws SQLException {
       return supplierDAO.delete(supplier_id);
    }

    public ArrayList<SupplierDTO> search(String search) throws SQLException {
        ArrayList<Supplier>supplierList = supplierDAO.search(search);
        ArrayList<SupplierDTO> dtos = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            dtos.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getAddress(),supplier.getContact()));
        }
        return dtos;
    }
}
