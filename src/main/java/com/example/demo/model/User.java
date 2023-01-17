package com.example.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Component
@Table(name = "users")
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "email")
   @NotEmpty(message = "Required field")
   @Email(message = "invalid e-mail")
   private String username;

   @Column(name = "name")
   @NotEmpty(message = "Required field")
   @Size(min = 2, max = 25, message = "Invalid name")
   private String name;

   @Column(name = "last_name")
   @NotEmpty(message = "Required field")
   @Size(min = 2, max = 25, message = "Invalid surname")
   private String lastName;

   @Column(name = "age")
   @Min(value = 0, message = "Invalid age")
   private int age;

   @Column(name = "password")
   @NotEmpty(message = "Required field")
   @Size(min = 2, message = "Invalid password")
   private String password;

   @ManyToMany(cascade = CascadeType.PERSIST)
   @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {
   }

   public User(int id, String username, String name, String lastName, int age, String password, Set<Role> roles) {
      this.id = id;
      this.username = username;
      this.name = name;
      this.lastName = lastName;
      this.age = age;
      this.password = password;
      this.roles = roles;
   }

   public User(String username, String name, String lastName, int age, String password, Set<Role> roles) {
      this.username = username;
      this.name = name;
      this.lastName = lastName;
      this.age = age;
      this.password = password;
      this.roles = roles;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   public String getPassword() {
      return password;
   }

   public String getUsername() {
      return username;
   }
   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
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

   public void setPassword(String password) {
      this.password = password;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   public void setUsername(String login) {
      this.username = login;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(id, user.id) &&
              Objects.equals(age, user.age) &&
              Objects.equals(name, user.name) &&
              Objects.equals(lastName, user.lastName) &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}