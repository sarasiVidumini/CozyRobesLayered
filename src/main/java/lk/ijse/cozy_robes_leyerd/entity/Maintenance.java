package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Maintenance {
    private String maintenanceId;
    private String materialId;
    private String sectionId;
    private Date maintenanceDate;
    private String maintenanceStatus;
    private double cost;

    public Maintenance(){}

}
