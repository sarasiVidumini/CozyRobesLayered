package lk.ijse.cozy_robes_leyerd.bo.custom.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.QuickcheckBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.QuickcheckDAO;
import lk.ijse.cozy_robes_leyerd.dto.QuickcheckDTO;
import lk.ijse.cozy_robes_leyerd.entity.Quickcheck;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuickcheckBOImpl implements QuickcheckBO {

    private QuickcheckDAO quickcheckDAO = (QuickcheckDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUICKCHECK);

    public String getNextId() throws SQLException {
        return quickcheckDAO.getNextId();
    }


    public boolean save(QuickcheckDTO quickcheckDto) throws SQLException {
       return quickcheckDAO.save(new Quickcheck(quickcheckDto.getCheckId(),quickcheckDto.getMaintenanceId(),quickcheckDto.getCheckType(),quickcheckDto.getStatus()));
    }

    public ArrayList<QuickcheckDTO> getAll() throws SQLException {
        ArrayList<Quickcheck>  list = quickcheckDAO.getAll();
        ArrayList<QuickcheckDTO> quickcheckList = new ArrayList<>();
        for (Quickcheck quickcheck : list) {
            quickcheckList.add(new QuickcheckDTO(quickcheck.getCheckId(), quickcheck.getMaintenanceId(), quickcheck.getCheckType(), quickcheck.getStatus()));
        }
        return quickcheckList;
    }


    public boolean  update(QuickcheckDTO quickcheckDto) throws SQLException {
       return quickcheckDAO.update(new Quickcheck(quickcheckDto.getCheckId(),quickcheckDto.getMaintenanceId(),quickcheckDto.getCheckType(),quickcheckDto.getStatus()));
    }

    public boolean delete(String check_id) throws SQLException {
       return quickcheckDAO.delete(check_id);
    }

    public ArrayList<QuickcheckDTO> search(String search) throws SQLException {
        ArrayList<Quickcheck> quickchecks = quickcheckDAO.search(search);
        ArrayList<QuickcheckDTO> dtos = new ArrayList<>();
        for (Quickcheck quickcheck : quickchecks) {
            dtos.add(new QuickcheckDTO(
                    quickcheck.getCheckId(),
                    quickcheck.getMaintenanceId(),
                    quickcheck.getCheckType(),
                    quickcheck.getStatus()
            ));
        }
        return dtos;
    }


}
