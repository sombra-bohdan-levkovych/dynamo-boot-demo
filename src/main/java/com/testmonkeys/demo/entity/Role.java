package com.testmonkeys.demo.entity;

import com.testmonkeys.demo.utils.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"users"})
@ToString(callSuper = true, exclude = {"users"})
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    @Column(name = "authority")
    private RoleType roleType;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> users;

    @Override
    public String getAuthority() {
        return roleType.getValue();
    }

}