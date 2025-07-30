package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class Customer implements Serializable {
    private String customerId;
    private String name;
    private String phone;
    private String email;


    public Customer(){}

}

