package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Employee {
    private String employeeId;
    private String userId;
    private String name;
    private String role;
    private double salary;

    public Employee(){}

}
