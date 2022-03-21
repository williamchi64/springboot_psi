package xyz.chichistudy.springboot.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Size(min = 2, max = 50, message = "{department.name.size}")
	private String name;

	@OneToMany(mappedBy = "department") // field name in Employee
	@OrderBy("id ASC") // DESC
	private Set<Employee> employees = new LinkedHashSet<>();
}
