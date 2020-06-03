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
}
