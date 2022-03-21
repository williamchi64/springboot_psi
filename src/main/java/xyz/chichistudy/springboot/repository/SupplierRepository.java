package xyz.chichistudy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.chichistudy.springboot.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
}
