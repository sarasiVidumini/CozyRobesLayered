package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {
    private String supplierId;
    private String name;
    private String address;
    private String contact;

}
