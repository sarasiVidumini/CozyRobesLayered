package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTM {
    private String employeeId;
    private String userId;
    private String name;
    private String role;
    private double salary;

}
