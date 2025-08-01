package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class Quickcheck {
    private String checkId;
    private String maintenanceId;
    private String checkType;
    private String status;

    public Quickcheck(){}
}
