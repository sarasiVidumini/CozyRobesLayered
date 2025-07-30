package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MaintenanceTM {
    private String maintenanceId;
    private String materialId;
    private String sectionId;
    private String maintenanceDate;
    private String maintenanceStatus;
    private double cost;

}
