package pl.sagiton.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by szymon on 09.03.16.
 */

@Entity
@Table(name = "ROLE")
public class Role {


    @Id
    @Column(name = "roleId")
    @GeneratedValue
    @Setter @Getter private Integer roleId;

    @Column(name = "role")
    @Setter @Getter private String role;


    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles", cascade=CascadeType.ALL)
    @Setter @Getter private Set<MyUser> users = new HashSet<MyUser>(0);
}