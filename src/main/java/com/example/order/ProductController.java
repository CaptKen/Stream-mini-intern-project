package com.example.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableAutoConfiguration
@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/product")
	public List<Product> getCompany() {
		
	   return productService.getAllProduct();
	}
}
