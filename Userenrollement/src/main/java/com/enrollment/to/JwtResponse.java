package com.enrollment.to;

import java.util.Date;

import com.enrollment.model.Role;

public class JwtResponse {
	
	private String token;
	
  	private String type = "Bearer";
  	
  	
  	private long id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String email;
	
	private Date dob;
	
	private String contact;
	
	private Role role;
	
	private Date createdon;

	public JwtResponse(String token,long id, String firstname, String lastname, String username,
			String email, Date dob, String contact, Role role, Date createdon) {
		super();
		this.token = token;
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.dob = dob;
		this.contact = contact;
		this.role = role;
		this.createdon = createdon;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	


}
