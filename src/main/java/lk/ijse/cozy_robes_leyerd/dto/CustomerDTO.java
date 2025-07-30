package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerDTO {
    private String customerId;
    private String name;
    private String phone;
    private String email;

}
