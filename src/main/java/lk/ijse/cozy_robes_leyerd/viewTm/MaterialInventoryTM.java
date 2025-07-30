package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MaterialInventoryTM {
    private String materialId;
    private String supplierId;
    private String materialName;
    private int quantity;

}
