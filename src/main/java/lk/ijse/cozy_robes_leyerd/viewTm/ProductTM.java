package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTM {
    private String productId;
    private String name;
    private int quantity;
    private String category;
    private double unitPrice;

}
