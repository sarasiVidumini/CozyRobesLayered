package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.QuickcheckDTO;
import lk.ijse.cozy_robes_leyerd.entity.Quickcheck;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QuickcheckBO extends SuperBO {
    public String getNextId() throws SQLException;
    public boolean save(QuickcheckDTO quickcheckDto) throws SQLException;
    public ArrayList<QuickcheckDTO> getAll() throws SQLException;
    public boolean  update(QuickcheckDTO quickcheckDto) throws SQLException;
    public boolean delete(String check_id) throws SQLException;
    public ArrayList<QuickcheckDTO> search(String search) throws SQLException;
}
