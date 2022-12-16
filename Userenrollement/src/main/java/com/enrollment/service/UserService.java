package com.enrollment.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.enrollment.to.Signupdto;

public interface UserService {
	
	public ResponseEntity<?> registeration(Signupdto signuodto);
	
	public void xlReportDetails(Signupdto signupdto,HttpServletRequest request,HttpServletResponse response);

}
