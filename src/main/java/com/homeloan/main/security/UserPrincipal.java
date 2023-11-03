package com.homeloan.main.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeloan.main.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPrincipal  implements UserDetails{

	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public static UserPrincipal create(User user) {
		
	List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserPrincipal(user.getId(), user.getFirstName
				(), user.getLastName(), user.getUsername(),
				user.getEmail(), user.getPassword(), authorities);
 }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return authorities == null ? null : new ArrayList<>(authorities) ;
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
