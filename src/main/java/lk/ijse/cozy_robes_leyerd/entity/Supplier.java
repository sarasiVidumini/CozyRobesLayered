package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Supplier {
    private String supplierId;
    private String name;
    private String address;
    private String contact;

    public Supplier(){}

}
