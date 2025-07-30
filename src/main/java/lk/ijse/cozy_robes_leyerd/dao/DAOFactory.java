package lk.ijse.cozy_robes_leyerd.dao;

import lk.ijse.cozy_robes_leyerd.dao.Impl.CustomerDAOImpl;
import lk.ijse.cozy_robes_leyerd.dao.Impl.DeliveryDAOImpl;
import lk.ijse.cozy_robes_leyerd.dao.Impl.EmployeeDAOImpl;
import lk.ijse.cozy_robes_leyerd.dao.Impl.MaintenanceDAOImpl;
import lk.ijse.cozy_robes_leyerd.dao.custom.CustomerDAO;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER ;
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
                case CUSTOMER:
                    return new CustomerDAOImpl();

                default:
                    return null;
        }

    }
}
