package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class MaterialInventory {
    private String materialId;
    private String supplierId;
    private String materialName;
    private int quantity;

    public MaterialInventory(){}
}
