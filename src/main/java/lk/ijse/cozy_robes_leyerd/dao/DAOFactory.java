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
        CUSTOMER , DELIVERY , EMPLOYEE , MAINTENANCE , MATERIALINVENTORY , QUICKCHECK , SUPPLIER , USER , WAREHOUSE , PRODUCT , PAYMENT ,ORDERS , ORDERDETAILS;
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
                case QUICKCHECK:
                    return new QuickcheckDAOImpl();
                case SUPPLIER:
                    return new SupplierDAOImpl();
                case USER:
                    return new UserDAOImpl();
                case WAREHOUSE:
                    return new WarehouseDAOImpl();
                case PRODUCT:
                    return new ProductDAOImpl();
                case PAYMENT:
                    return new PaymentDAOImpl();
                case ORDERS:
                    return new OrdersDAOImpl();
                case ORDERDETAILS:
                    return new OrderDetailsDAOImpl();

                default:
                    return null;
        }

    }
}
