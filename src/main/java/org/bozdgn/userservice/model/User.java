package org.bozdgn.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    Long id;

    @Column(name = "username", nullable = false, length = 20)
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email", length = 50)
    String email;
    @Column(name = "is_admin", nullable = false)
    Boolean isAdmin;
    @Column(name = "first_name", length = 50)
    String firstName;
    @Column(name = "last_name", length = 50)
    String lastName;
    @Column(name = "telephone", length = 11)
    String telephone;
}