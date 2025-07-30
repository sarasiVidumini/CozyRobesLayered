package lk.ijse.cozy_robes_leyerd.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private String paymentId;
    private String orderId;
    private String paymentMethod;
    private double totalAmount;

}
