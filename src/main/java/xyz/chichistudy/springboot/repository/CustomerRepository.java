package xyz.chichistudy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.chichistudy.springboot.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
