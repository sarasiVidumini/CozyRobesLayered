package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class Delivery {
    private String deliveryId;
    private String orderId;
    private String address;
    private String status;

    public Delivery(){}
}
