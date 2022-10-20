package com.viz.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viz.pet.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Customer findByEmail(String email);
}
