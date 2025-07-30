package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceDTO {
    private String maintenanceId;
    private String materialId;
    private String sectionId;
    private Date maintenanceDate;
    private String maintenanceStatus;
    private double cost;


}
