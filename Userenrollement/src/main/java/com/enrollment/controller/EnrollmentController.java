package com.enrollment.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.jwt.JwtUtils;
import com.enrollment.service.UserService;
import com.enrollment.serviceImpl.UserDetailsImpl;
import com.enrollment.to.JwtResponse;
import com.enrollment.to.Signupdto;
import com.enrollment.to.Singindto;

@RestController
@RequestMapping("/api/auth/")
public class EnrollmentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	  AuthenticationManager authenticationManager;
	
	@Autowired
	  JwtUtils jwtUtils;
	
	
	
	@PostMapping("/enroll")
	public ResponseEntity<?> enrollemnt(@RequestBody Signupdto signup){
		return userService.registeration(signup);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@Valid @RequestBody Singindto signindto){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signindto.getUsername(), signindto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
               userDetails.getId(), 
               userDetails.getFirstname(),
               userDetails.getLastname(),
                userDetails.getUsername(), 
                userDetails.getEmail(),
                userDetails.getDob(),
                userDetails.getContact(),
               userDetails.getRole(),
               userDetails.getcreatedon()));
		
	}
	
	  	
	
	
	@PostMapping("/reports")
	public ResponseEntity<?> getReport(@RequestBody  Signupdto signupdto,HttpServletRequest request,HttpServletResponse response){		
		userService.xlReportDetails(signupdto, request, response);
		return null;

	}
	
}
