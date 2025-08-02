package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Warehouse {
    private String sectionId;
    private String productId;
    private int capacity;
    private String location;

    public Warehouse(){}

}
