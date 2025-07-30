package lk.ijse.cozy_robes_leyerd.viewTm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTM {
    private String userId;
    private String role;
    private String name;
    private String contact;
    private String password;

}
