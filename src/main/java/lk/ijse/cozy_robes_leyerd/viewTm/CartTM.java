package lk.ijse.cozy_robes_leyerd.viewTm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class CartTM {
    private String customerId;
    private String productId;
    private String productName;
    private int cartQty;
    private double unitPrice;
    private double total;
    private String paymentMethod;
    private Button btnRemove;


}
