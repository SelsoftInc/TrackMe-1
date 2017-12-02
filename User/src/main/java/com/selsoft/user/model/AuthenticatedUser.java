package com.selsoft.user.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Holds the info for a authenticated user (Principal)
 * 
 * @author pascal alma
 */
public class AuthenticatedUser implements UserDetails {

	private final Long id;
	private final String username;
	private final String token;
	private final Long iat;
	private final Long exp;
	private final Collection<? extends GrantedAuthority> authorities;

	public AuthenticatedUser(Long id, String username, String token, Long iat, Long exp,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
		this.iat = iat;
		this.exp = exp;
		this.authorities = authorities;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	public String getToken() {
		return token;
	}

	public Long getIat() {
		return iat;
	}

	public Long getExp() {
		return exp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

}
