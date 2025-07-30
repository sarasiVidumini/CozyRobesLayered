package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuickcheckTM {
    private String checkId;
    private String maintenanceId;
    private String checkType;
    private String status;

}
