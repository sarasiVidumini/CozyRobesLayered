package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.MaterialInventoryBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.MaterialInventoryDAO;
import lk.ijse.cozy_robes_leyerd.dto.MaterialInventoryDTO;
import lk.ijse.cozy_robes_leyerd.entity.MaterialInventory;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialInventoryBOImpl implements MaterialInventoryBO {

    private MaterialInventoryDAO materialInventoryDAO = (MaterialInventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MATERIALINVENTORY);

    public String getNextId() throws SQLException {
      return materialInventoryDAO.getNextId();
    }

    public boolean save(MaterialInventoryDTO materialInventoryDto) throws SQLException {
       return materialInventoryDAO.save(new MaterialInventory(materialInventoryDto.getMaterialId(),materialInventoryDto.getSupplierId(),materialInventoryDto.getMaterialName(),materialInventoryDto.getQuantity()));
    }

    public ArrayList<MaterialInventoryDTO> getAll() throws SQLException {
        ArrayList<MaterialInventory> materialList = materialInventoryDAO.getAll();
        ArrayList<MaterialInventoryDTO> materialInventoryDTOS = new ArrayList<>();
        for (MaterialInventory materialInventory : materialList) {
            materialInventoryDTOS.add(new MaterialInventoryDTO(materialInventory.getMaterialId(),materialInventory.getSupplierId(),materialInventory.getMaterialName(),materialInventory.getQuantity()));
        }
        return materialInventoryDTOS;
    }


    public boolean update(MaterialInventoryDTO materialInventoryDto) throws SQLException {
       return materialInventoryDAO.update(new MaterialInventory(materialInventoryDto.getMaterialId(),materialInventoryDto.getSupplierId(),materialInventoryDto.getMaterialName(),materialInventoryDto.getQuantity()));
    }

    public boolean delete(String material_id) throws SQLException {
          return materialInventoryDAO.delete(material_id);
    }

    public ArrayList<MaterialInventoryDTO> search(String search) throws SQLException {
        ArrayList<MaterialInventory> materialList = materialInventoryDAO.search(search);
        ArrayList<MaterialInventoryDTO> dtos = new ArrayList<>();
        for (MaterialInventory materialInventory : materialList) {
            dtos.add(new MaterialInventoryDTO(materialInventory.getMaterialId(),materialInventory.getSupplierId(),materialInventory.getMaterialName(),materialInventory.getQuantity()));
        }
        return dtos;
    }
}
