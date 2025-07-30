package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDTO {
    private String deliveryId;
    private String orderId;
    private String address;
    private String status;

}
