package com.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrollment.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByRole(String role);

}
