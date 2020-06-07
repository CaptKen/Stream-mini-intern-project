package com.example.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdertService {
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrder() {
		return  orderRepository.showingChange();
		}
//	public List<Order> getAll() {
//		return  orderRepository.findAll();
//		}
	
//	public List<Order> findByproduct_name(String name) {
//		return  orderRepository.searchByproduct_nameLike(name);
//		}
//	
//	public List<Order> findByOrderID(Integer id) {
//		return  orderRepository.searchByOrderIDLike(id);
//		}
	public List<Order> findByOrderID(String id) {
		return  orderRepository.search(id);
		}
	public List<Order> findByAllCol(String id, String code, String name, String pro, String com, Integer ppu, Integer unit, Float vat, Integer total){
		return orderRepository.search0(id, code, name, pro, com, ppu, unit, vat, total);
		}
	public List<Order> getShowing(){
		return orderRepository.showingOrder();
	}
	public void setToActive(){
		 orderRepository.activeOrder();
	}
	public void setToShowing(Integer id, String orderID) {
		orderRepository.setNotShowing(orderID);
		orderRepository.setShowing(id);
	}
	public void setToShowingAtBegin(String orderID) {
		
		orderRepository.setShowingByOrderId(orderID);
	}

}
