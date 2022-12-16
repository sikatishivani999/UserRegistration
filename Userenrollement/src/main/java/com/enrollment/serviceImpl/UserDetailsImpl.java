package com.enrollment.serviceImpl;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.enrollment.model.Role;
import com.enrollment.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Long getId() {
		return id;
	}

	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String email;
	@JsonIgnore
	private String password;
	
	private Date dob;
	private String contact;
	
	private Role role;
	
	private Date createdon;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserDetailsImpl(Long id, String firstname,String lastname,String username, String email, String password, Date dob,String contact,
		      Role role, Date createdon) {
		    this.id = id;
		    this.firstname = firstname;
		    this.lastname = lastname;
		    this.username = username;
		    this.email = email;
		    this.password = password;
		    this.dob = dob;
		    this.contact = contact;
		    this.role = role;
		    this.createdon = createdon;
		  }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public Date getDob() {
		return dob;
	}
	
	public String getContact() {
		return contact;
	}

	public Role getRole() {
		return role;
	}

	public Date getcreatedon() {
		return createdon;
	}

	public static UserDetailsImpl build(User user) {
	    return new UserDetailsImpl(
	        user.getId(),
	        user.getFirstname(),
	        user.getLastname(),
	        user.getUsername(), 
	        user.getEmail(),
	        user.getPassword(),
	        user.getDob(),
	        user.getContact(),
	        user.getRole(),
	        user.getCreatedon());
	  }


}
