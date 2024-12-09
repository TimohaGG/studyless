package com.studyless.studylesskidscrm.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50, message = "Від 5 до 50 символів")
    private String username;
    @Size(min = 8,message = "Від 8 символів")
    private String password;

    @OneToMany(mappedBy = "teacher")
    private List<IndividualTeacher> individual_teachers;

    @OneToMany(mappedBy = "manager")
    private List<IndividualLessons> individualLearners;


    public void setUsername(@Size(min = 5, max = 50, message = "Від 5 до 50 символів") String username) {
        this.username = username;
    }

    public void setPassword(@Size(min = 8, max = 20, message = "Від 8 до 20 символів") String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    private String passConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
