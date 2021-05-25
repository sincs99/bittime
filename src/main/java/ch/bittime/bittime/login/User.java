package ch.bittime.bittime.login;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Pascal
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") //MySQL Data Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //https://thorben-janssen.com/jpa-generate-primary-keys/
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * @author Dominic, Andre
     * street, city, state
     */

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;
}