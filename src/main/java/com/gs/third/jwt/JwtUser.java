package com.gs.third.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs.model.entity.jpa.db1.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

/**
 * @author guozy
 * @date 2018-11-23
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {

  private static final long serialVersionUID = -7902680224827400883L;

  @JsonIgnore
  private final Long id;

  private final String username;

  @JsonIgnore
  private final String password;

  // private final String avatar;

  private final String email;

  // private final Hospital hospital;

  // private final String available;

  // private final Set<Role> subRoles;

  @JsonIgnore
  private final Collection<? extends GrantedAuthority> authorities;

  private final boolean enabled;

  // private Timestamp createTime;

  // @JsonIgnore
  // private final Date lastPasswordResetDate;

  // private final String dataScope;

  public JwtUser(User user) {
    id = user.getId();
    username = user.getUserName();
    email = user.getEmail();
    password = user.getPassword();
    enabled = !user.getDeleted();
    // authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    authorities = null;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
      return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
      return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
      return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
      return password;
  }

  @Override
  public boolean isEnabled() {
      return enabled;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  // public Collection getRoles() {
  //     return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
  // }
}
