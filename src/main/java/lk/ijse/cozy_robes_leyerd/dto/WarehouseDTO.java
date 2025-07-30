package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDTO {
    private String sectionId;
    private String productId;
    private int capacity;
    private String location;

}
