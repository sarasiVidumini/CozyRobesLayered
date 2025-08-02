package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.WarehouseBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.WarehouseDAO;
import lk.ijse.cozy_robes_leyerd.dto.WarehouseDTO;
import lk.ijse.cozy_robes_leyerd.entity.Warehouse;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseBOImpl implements WarehouseBO {

    private WarehouseDAO warehouseDAO = (WarehouseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.WAREHOUSE);

    public String getNextId() throws SQLException {
        return warehouseDAO.getNextId();
    }

    public boolean save(WarehouseDTO entity) throws SQLException {
       return warehouseDAO.save(new Warehouse(entity.getSectionId(),entity.getProductId(),entity.getCapacity(),entity.getLocation()));
    }

    public ArrayList<WarehouseDTO> getAll() throws SQLException {
        ArrayList<Warehouse> sections = warehouseDAO.getAll();
        ArrayList<WarehouseDTO> warehouseDtoArrayList = new ArrayList<>();
        for (Warehouse section : sections) {
            warehouseDtoArrayList.add(new WarehouseDTO(section.getSectionId(),section.getProductId(),section.getCapacity(),section.getLocation()));
        }
        return warehouseDtoArrayList;
    }


    public boolean  update(WarehouseDTO entity) throws SQLException {
       return warehouseDAO.update(new Warehouse(entity.getSectionId(),entity.getProductId(),entity.getCapacity(),entity.getLocation()));
    }

    public boolean delete(String section_id) throws SQLException{
       return warehouseDAO.delete(section_id);
    }

    public ArrayList<WarehouseDTO> search(String search) throws SQLException {
        ArrayList<Warehouse> sections = warehouseDAO.search(search);
        ArrayList<WarehouseDTO> dtos = new ArrayList<>();
        for (Warehouse section : sections) {
            dtos.add(new WarehouseDTO(section.getSectionId(),section.getProductId(),section.getCapacity(),section.getLocation()));
        }
        return dtos;
    }


}
