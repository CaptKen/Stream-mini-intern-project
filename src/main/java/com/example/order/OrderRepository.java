package com.example.order;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//	@Query("SELECT o FROM Order o WHERE o.product_name LIKE %:product_name%")
//    List<Order> searchByproduct_nameLike(@Param("product_name") String name);
//	
//	@Query("SELECT o FROM Order o WHERE o.orderID LIKE :orderiD")
//    List<Order> searchByOrderIDLike(@Param("orderiD") Integer id);
//	
//	@Query("SELECT o FROM Order o WHERE o.orderID = :orderiD  OR o.product_name LIKE %:product_name% OR o.product_code = :product_code")
//	List<Order> search(@Param("orderiD") Integer id, @Param("product_name") String name, @Param("product_code") Integer code);
	
	@Query("SELECT o FROM Order o WHERE o.orderID LIKE :id OR o.product_code LIKE :code OR o.product_name LIKE %:name% OR o.price_per_unit LIKE :ppu OR o.unit LIKE :unit OR o.vat LIKE :vat OR o.total_price LIKE :total")
	List<Order> search0(@Param("id") Integer id, @Param("code") Integer code, @Param("name") String name, @Param("ppu") Integer ppu,@Param("unit") Integer unit,@Param("vat") Float vat, @Param("total") Integer total);
}

