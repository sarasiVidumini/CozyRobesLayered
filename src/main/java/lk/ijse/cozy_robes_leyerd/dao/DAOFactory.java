package lk.ijse.cozy_robes_leyerd.dao;

import lk.ijse.cozy_robes_leyerd.dao.Impl.*;
import lk.ijse.cozy_robes_leyerd.dao.custom.CustomerDAO;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER , DELIVERY , EMPLOYEE , MAINTENANCE , MATERIALINVENTORY;
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
                case CUSTOMER:
                    return new CustomerDAOImpl();
                case DELIVERY:
                    return new DeliveryDAOImpl();
                case EMPLOYEE:
                    return new EmployeeDAOImpl();
                case MAINTENANCE:
                    return new MaintenanceDAOImpl();
                case MATERIALINVENTORY:
                    return new MaterialInventoryDAOImpl();
                default:
                    return null;
        }

    }
}
