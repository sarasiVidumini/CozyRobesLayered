package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class OrderDetails {
    private String orderDetailId;
    private String orderId;
    private String productId;
    private int quantity;
    private double priceAtPurchase;

    public OrderDetails(){}

}
