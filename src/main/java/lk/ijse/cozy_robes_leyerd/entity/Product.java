package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Product {
    private String productId;
    private String name;
    private int quantity;
    private String category;
    private double unitPrice;

    public Product(){}

}
