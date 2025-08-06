package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Orders {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String status;
    private String productId;

    public Orders(){}

}
