package xyz.chichistudy.springboot.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Integer amount;
	@ManyToOne
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Product product;
	@ManyToOne
	@JoinColumn(name = "purchase_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//	@JsonIgnoreProperties({"purchaseItems", "employee"})
	private Purchase purchase;
}
