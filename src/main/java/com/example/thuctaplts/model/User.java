package com.example.thuctaplts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User extends BaseEntity implements UserDetails {

    private long point;

    private String userName;

    private String email;

    private String name;

    private int phoneNumber;

    private String password;

    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rankCustomerID", foreignKey = @ForeignKey(name = "fk_User_RankCustomer"), nullable = false)
    @JsonManagedReference
    private RankCustomer rankCustomer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Bill> bills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<ConfirmEmail> confirmEmails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<RefreshToken> refreshTokens;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userStatusID", foreignKey = @ForeignKey(name = "fk_User_UserStatus"), nullable = false)
    @JsonManagedReference
    private UserStatus userStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleID", foreignKey = @ForeignKey(name = "fk_User_Roles"), nullable = false)
    @JsonManagedReference
    private Roles roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList=  new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + getRoles().getRoleName().toUpperCase()));
        return authorityList;
    }

    public String getUserName() {
        return userName;
    }



    @Override
    public String getPassword() {
        return password;
    }

    //get Username của UserDetail
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
