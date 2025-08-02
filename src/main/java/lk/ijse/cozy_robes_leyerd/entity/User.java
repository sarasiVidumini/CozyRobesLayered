package lk.ijse.cozy_robes_leyerd.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class User {
    private String userId;
    private String role;
    private String name;
    private String contact;
    private String password;

    public User(){}

}
