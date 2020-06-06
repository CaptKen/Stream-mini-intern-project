package com.example.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdertService {
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrder() {
		return  orderRepository.findAll();
		}
	
//	public List<Order> findByproduct_name(String name) {
//		return  orderRepository.searchByproduct_nameLike(name);
//		}
//	
//	public List<Order> findByOrderID(Integer id) {
//		return  orderRepository.searchByOrderIDLike(id);
//		}
//	public List<Order> findByOrderIDOrproduct_nameOrproduct_code(Integer id, String name, Integer code) {
//		return  orderRepository.search(id, name, code);
//		}
	public List<Order> findByAllCol(String id, String code, String name, String pro, String com, Integer ppu, Integer unit, Float vat, Integer total){
		return orderRepository.search0(id, code, name, pro, com, ppu, unit, vat, total);
	}
}
