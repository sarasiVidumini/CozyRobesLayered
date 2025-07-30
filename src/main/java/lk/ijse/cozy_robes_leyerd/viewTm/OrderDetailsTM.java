package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsTM {
    private String orderDetailId;
    private String orderId;
    private String productId;
    private  int quantity;
    private double priceAtPurchase;

}
