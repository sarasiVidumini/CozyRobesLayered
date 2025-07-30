package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDTO {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String status;
    private String productId;


}
