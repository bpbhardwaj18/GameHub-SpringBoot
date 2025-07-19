package com.bp.SpringSecurityEx.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import com.bp.SpringSecurityEx.pojo.User;
import com.bp.SpringSecurityEx.repository.UserRepo;
import com.bp.SpringSecurityEx.securityConf.JWTService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTService jwtService;
	@Autowired
	UserRepo repo;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@PostMapping("/signin")
	public ResponseEntity<Map<String,Object>> authenticateUser(@RequestBody User user) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
			if (authentication.isAuthenticated()) {
				Map<String,Object> map = new HashMap<>();
				String token = jwtService.generateToken(user.getName());
				Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
				map.put("token", token);
				map.put("roles", roles);
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(Map.of("error", "Not able to Authenticate"), HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(Map.of("error",e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		try {
			System.out.println(user);
			user.setPassword(encoder.encode(user.getPassword()));
			repo.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/session")
	public ResponseEntity<String> sessionCheck() {
		try {
			return new ResponseEntity<>("Success ", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}