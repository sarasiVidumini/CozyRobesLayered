package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTM {
    private String paymentId;
    private String orderId;
    private String paymentMethod;
    private double totalAmount;

}
