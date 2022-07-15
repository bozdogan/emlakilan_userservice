package org.bozdgn.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
    String username;
    String password;
    String email;
    Boolean isAdmin;
    String firstName;
    String lastName;
    String telephone;
}
