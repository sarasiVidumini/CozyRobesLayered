package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Payment {
    private String paymentId;
    private String orderId;
    private String paymentMethod;
    private double totalAmount;

    public Payment(){}

}
