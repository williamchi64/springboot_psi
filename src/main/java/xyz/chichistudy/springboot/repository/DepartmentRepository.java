package xyz.chichistudy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.chichistudy.springboot.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}
