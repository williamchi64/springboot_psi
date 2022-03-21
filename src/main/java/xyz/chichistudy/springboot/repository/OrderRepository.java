package xyz.chichistudy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.chichistudy.springboot.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}