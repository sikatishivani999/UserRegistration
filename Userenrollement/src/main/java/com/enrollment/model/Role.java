package com.enrollment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String role;
	
	public Role(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Role(long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	

}
