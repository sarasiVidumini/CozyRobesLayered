package lk.ijse.cozy_robes_leyerd.bo;

import lk.ijse.cozy_robes_leyerd.bo.custom.CustomerBO;
import lk.ijse.cozy_robes_leyerd.bo.impl.CustomerBOImpl;
import lk.ijse.cozy_robes_leyerd.bo.impl.DeliveryBOImpl;
import lk.ijse.cozy_robes_leyerd.bo.impl.EmployeeBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER , DELIVERY , EMPLOYEE;
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case CUSTOMER:
                return new CustomerBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();

                default:
                    return null;
        }
    }
}
