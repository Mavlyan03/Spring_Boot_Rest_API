package com.example.spring_boot_rest_api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "role_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_gen",sequenceName = "role_seq",allocationSize = 1)
    private Long id;
    private String roleName;

    @ManyToMany(mappedBy = "roles",cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<User> users;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
