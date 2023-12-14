package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 유저 인증 객체
@Getter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	@Setter
	private Map<String, Object> attributes;
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("" + UserRole.CLIENT));
		
		return new UserPrincipal(
					user.getId(),
					user.getEmail(),
					user.getPassword(),
					authorities,
					null
				);
	}
	
	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}

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
