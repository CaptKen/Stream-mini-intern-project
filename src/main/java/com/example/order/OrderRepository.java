package com.example.order;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Query("SELECT o FROM Order o WHERE o.orderID LIKE %:id% OR o.product_code LIKE %:code% OR o.product_name LIKE %:name% OR o.product LIKE %:pro% OR o.company LIKE %:com% OR o.price_per_unit LIKE :ppu OR o.unit LIKE :unit OR o.vat LIKE :vat OR o.total_price LIKE :total")
	List<Order> search0(@Param("id") String id, @Param("code") String code, @Param("name") String name, @Param("pro") String pro, @Param("com") String com, @Param("ppu") Integer ppu,@Param("unit") Integer unit,@Param("vat") Float vat, @Param("total") Integer total);

	@Query("select o from Order o where o.showInHome = 1 AND o.active = 1")
	List<Order> showingOrder();
	
	@Modifying
	@Transactional
	@Query("update Order o set o.active=1 where 1 = 1")
	void activeOrder();
	@Modifying
	@Transactional
	@Query("update Order o set o.showInHome=1 where o.id = :id")
	void setShowing(@Param("id") Integer id);
	@Modifying
	@Transactional
	@Query("update Order o set o.showInHome=0 where o.orderID like :orderID")
	void setNotShowing(@Param("orderID") String orderID);
	
//	UPDATE `order_product` SET `active` = '1', `show_in_home` = '1' WHERE `order_product`.`id` = 31;
	
}

