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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "purchase")
@Getter
@Setter
public class Purchase {
	final static long time = System.currentTimeMillis();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{purchase.date.notnull}")
//	@DatePoint(comparePattern = ComparePattern.FutureOrPresent)
	private Date date = new Date();

	@ManyToOne
	@JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Employee employee;

	@OneToMany(mappedBy = "purchase")
	private Set<PurchaseItem> purchaseItems = new LinkedHashSet<>();

	public Integer getTotal() {
		return purchaseItems.size() == 0? 0: purchaseItems.stream().mapToInt(
			pi->pi.getAmount() * pi.getProduct().getCost()
		).sum();
	}
}
