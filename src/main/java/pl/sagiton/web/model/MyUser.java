package pl.sagiton.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="USERS")
public class MyUser {

    @Id
    @Column(name = "id")
    @GeneratedValue
    @Setter @Getter private Integer id;

    @Column(name = "username")
    @Setter @Getter private String username;

    @Column(name = "password")
    @Setter @Getter private String password;

    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = { @JoinColumn(name = "roleId")})
    @Setter @Getter private Set<Role> roles = new HashSet<Role>(0);



}
