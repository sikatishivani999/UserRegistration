package com.enrollment.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enrollment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("FROM User WHERE createdon >= :startDate AND createdon <= :endDate")
	List<User> getUserByCreatedon(String startDate,String endDate);

}
