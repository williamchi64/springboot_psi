package xyz.chichistudy.springboot.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{order.date.notnull}")
	private Date date;
	
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	@ManyToOne
	private Customer customer;
	
	@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	@ManyToOne
	private Employee employee;
	
	@OneToMany(mappedBy = "order")
	@OrderBy("id ASC")
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	
	public Integer getTotal() {
		return orderItems.size() == 0? 0: orderItems.stream().mapToInt(
			oi -> oi.getAmount() * oi.getProduct().getPrice()
		).sum();
	}
}
