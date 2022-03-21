package xyz.chichistudy.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xyz.chichistudy.springboot.entity.Product;
import xyz.chichistudy.springboot.entity.view.Inventory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT p.id, p.name, p.cost, p.price, "
			+ "(SELECT sum(amount) FROM purchase_item WHERE product_id = p.id limit 1) as amount1, "
			+ "(SELECT sum(amount) FROM order_item WHERE product_id = p.id limit 1) as amount2 "
			+ "FROM product p",
			nativeQuery = true)
	List<Inventory> queryInventories();
	
	@Query(value = "SELECT p.id, p.name, p.cost, p.price, "
			+ "(SELECT sum(amount) FROM purchase_item WHERE product_id = p.id limit 1) as amount1, "
			+ "(SELECT sum(amount) FROM order_item WHERE product_id = p.id limit 1) as amount2 "
			+ "FROM product p WHERE p.id=:id",
			nativeQuery = true)
	Inventory queryInventoryById(Long id);
}
