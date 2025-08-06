package lk.ijse.cozy_robes_leyerd.bo;

import lk.ijse.cozy_robes_leyerd.bo.custom.CustomerBO;
import lk.ijse.cozy_robes_leyerd.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER , DELIVERY , EMPLOYEE , MAINTENANCE , MATERIALINVENTORY , QUICKCHECK , SUPPLIER , USER , WAREHOUSE , PRODUCT , PAYMENT , ORDERS , ORDERDETAILS , CART;
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case CUSTOMER:
                return new CustomerBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case MAINTENANCE:
                return new MaintenanceBOImpl();
            case MATERIALINVENTORY:
                return new MaterialInventoryBOImpl();
            case QUICKCHECK:
                return new QuickcheckBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            case WAREHOUSE:
                return new WarehouseBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            case CART:
                return new CartBOImpl();

                default:
                    return null;
        }
    }
}
