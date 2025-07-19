package com.bp.SpringSecurityEx.securityConf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bp.SpringSecurityEx.pojo.User;
import com.bp.SpringSecurityEx.repository.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repo.findByName(username);
		if (user == null) {
			System.out.println(username);
			System.out.println("jbdhbshbhjbhdsa");
			throw new UsernameNotFoundException("Username not found");
		}
		System.out.println(user);
		return new MyUserDetails(user);
//		System.out.println(details.getPassword());
	}

}
