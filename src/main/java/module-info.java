module lk.ijse.cozy_robes_leyerd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;
    requires lombok;
    requires jdk.unsupported.desktop;
    requires java.mail;
    requires activation;
    requires net.sf.jasperreports.core;
    requires commons.beanutils;

    opens lk.ijse.cozy_robes_leyerd.controller to javafx.fxml;
    opens lk.ijse.cozy_robes_leyerd to javafx.fxml;
    opens lk.ijse.cozy_robes_leyerd.viewTm to javafx.fxml;

    exports lk.ijse.cozy_robes_leyerd.controller to javafx.fxml;
    exports lk.ijse.cozy_robes_leyerd;
    exports lk.ijse.cozy_robes_leyerd.viewTm;
}
