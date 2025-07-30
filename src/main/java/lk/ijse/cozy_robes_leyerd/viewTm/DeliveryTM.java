package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DeliveryTM {
    private String deliveryId;
    private String orderId;
    private String address;
    private String status;

}
