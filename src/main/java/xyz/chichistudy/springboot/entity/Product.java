package xyz.chichistudy.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Size(min = 2, max = 100, message = "{product.name.size}")
	private String name;
	
	@Column
	@NotNull(message = "{product.cost.notnull}")
	@Range(min = 10, max = 1000, message = "{product.cost.range}")
	private Integer cost;
	
	@Column
	@NotNull(message = "{product.price.notnull}")
	@Range(min = 10, max = 1000, message = "{product.price.range}")
	private Integer price;
}
