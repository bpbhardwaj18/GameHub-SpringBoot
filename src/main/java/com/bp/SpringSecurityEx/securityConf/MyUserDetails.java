package com.bp.SpringSecurityEx.securityConf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bp.SpringSecurityEx.pojo.User;

public class MyUserDetails implements UserDetails {

	private User user;

	public MyUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> col = new ArrayList<>();
		col.add(new SimpleGrantedAuthority("ROLE_User"));
		user.getRoles().stream().forEach(e -> col.add(new SimpleGrantedAuthority("ROLE_" + e)));
		 System.out.println("ROles" + col);
		return col;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getName();
	}

}
