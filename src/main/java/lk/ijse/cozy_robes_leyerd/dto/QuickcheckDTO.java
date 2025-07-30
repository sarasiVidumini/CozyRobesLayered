package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuickcheckDTO {
    private String checkId;
    private String maintenanceId;
    private String checkType;
    private String status;

}
