package com.exam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Marks this class as a JPA entity and maps it to the "users" table
@Entity
@Table(name = "users")
public class User implements UserDetails {

    // Primary key with auto-generation for the ID field
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // User-specific fields for storing login and profile data
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    // Marks whether the user is enabled (active) or disabled
    private boolean enabled = true;
    
    // User's profile picture or avatar (can be a URL or path)
    private String profile;

    // Relationship One user can have many roles
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore 
    // Prevents serialization of userRoles when returning a JSON response
    private Set<UserRole> userRoles = new HashSet<>();

   
    public User() {
    }

    //for many to many rel
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

  
    public User(Long id, String username, String password, String firstName, String lastName, String email, String phone, boolean enabled, String profile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.profile = profile;
    }
 
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Returns true to indicate that the account is not expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Returns true to indicate that the account is not locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Returns true to indicate that the credentials (password) are not expired
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Returns the authorities (roles) granted to the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        
        // Loop through user roles and add their authority (role name) to the set
        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });

        return set;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
