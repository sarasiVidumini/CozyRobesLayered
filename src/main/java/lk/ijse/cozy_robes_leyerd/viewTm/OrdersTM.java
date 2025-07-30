package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersTM {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String status;
    private String productId;

}
