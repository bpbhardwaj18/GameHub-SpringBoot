package com.bp.SpringSecurityEx.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bp.SpringSecurityEx.pojo.GameData;
import com.bp.SpringSecurityEx.pojo.User;
import com.bp.SpringSecurityEx.repository.GameDataRepo;
import com.bp.SpringSecurityEx.repository.UserRepo;
//import com.bp.SpringSecurityEx.securityConf.CustomAuthenticationProvider;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/game")
public class GameHubApi {

	@Autowired
	GameDataRepo repo;

//	@Autowired
//	CustomAuthenticationProvider obj;
//	@GetMapping("/getUsers")
//	public List<User> getUsers() {
//		return repo.findAll();
//	}
//
//	@GetMapping("/")
//	public String defaultPage() {
//		return "Bhanu";
//	}
//
//	@PostMapping("/addUser")
//	public User updateUser(@RequestBody User user) {
//		user.setPassword(encoder.encode(user.getPassword()));
//		return repo.save(user);
//	}
//
//	@GetMapping("/csrf")
//	public Object getCSRF(HttpServletRequest request) {
//		return request.getAttribute("_csrf");
//
//	}
	
	
	
	@GetMapping("/{genre}")
	public ResponseEntity<List> getMethodName(@PathVariable String genre) {
		List<GameData> data = repo.findByCategoryContaining(genre);
		return new ResponseEntity<List>(data,HttpStatus.OK);
	}
	
	@PostMapping("/addGame")
	public ResponseEntity<String> postMethodName(@RequestBody GameData data) {
		try {
		System.out.println(data);
		repo.save(data);
		return new ResponseEntity<>(data.toString(),HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.OK);
		}
	}
	

}
