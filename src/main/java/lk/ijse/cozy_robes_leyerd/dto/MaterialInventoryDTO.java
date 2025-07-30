package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MaterialInventoryDTO {
    private String materialId;
    private String supplierId;
    private String materialName;
    private int quantity;

}
