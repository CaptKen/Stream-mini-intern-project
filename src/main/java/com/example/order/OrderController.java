package com.example.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ch.qos.logback.core.Context;



@RestController
@EnableAutoConfiguration
@EnableScheduling
public class OrderController {
	@Autowired
    OrdertService orderservice;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	LineNotifyService lineNotifyService;
	
	
	List<Order> orders = new ArrayList();
//	@RequestMapping(value= "/create", method = RequestMethod.POST)
	@PostMapping("/create")
	public RedirectView createOrdersObject(@RequestParam("inText") String[] txt, @RequestParam("productName") String product, @RequestParam("companyName") String company, Model model) throws Exception {		
		
		String[] strs = txt;	
		int index = 0;
		System.out.println(strs[0]);
        for(int i=0; i<(strs.length)/7; i++) {
        	String order_id = strs[index];
        	String product_code = strs[index+1];
    		String product_name = strs[index+2];
    		int price_per_unit = Integer.parseInt(strs[index+3]);
    		int unit = Integer.parseInt(strs[index+4]);
    		float vat = Float.parseFloat(strs[index+5]);
    		int total_price = Integer.parseInt(strs[index+6]);
        	Order o = new Order();
        	o.setOrderID(order_id);
        	o.setProduct_code(product_code);
        	o.setProduct_name(product_name);
        	o.setProduct(product);
        	o.setCompany(company);
        	o.setPrice_per_unit(price_per_unit);
        	o.setUnit(unit);
        	o.setVat(vat);
        	o.setTotal_price(total_price);
        	orderRepository.save(o);
        	System.out.println(orderservice.findByOrderID(order_id).size());
        	if(orderservice.findByOrderID(order_id).size() == 1) {
        		orderservice.setToShowingAtBegin(order_id);
            }
        	index += 7;
        }
        lineNotifyService.sendLineNotifyMessages("สร้าง order เรียบร้อยละนะ");
        RedirectView redirectView  = new RedirectView();
        redirectView.setUrl("http://localhost:3000/index.html");
        return redirectView;
//        return "forward:http://localhost:3000/index.html#!/tab2";
	}
	

	
	@GetMapping("/order/show")
	public RedirectView setOrderActive(@RequestParam("ID") int id, @RequestParam("orderID") String orderID) throws Exception {
		orderservice.setToShowing(id, orderID);
//		lineNotifyService.sendLineNotifyMessages("Update OrderID "+ orderID + "ละนะ");
		lineNotifyService.sendLineNotifySticker("Update OrderID "+ orderID + " ละนะ", 2, 28);
	       RedirectView redirectView  = new RedirectView();
	        redirectView.setUrl("http://localhost:3000/index.html#!/tab2");
	        return redirectView;
	}
   
	 @GetMapping("/order")
	 public List<Order> getAllOrder() {
		   return orderservice.getAllOrder();
		}
	 
	 @GetMapping("/showingOrder")
	 public List<Order> showOrder() {
		 return orderservice.getShowing();
		}
	 
//	 @GetMapping("/active")
//	 @Scheduled(cron = "0 0/5  12-17 * * *")
	 @Scheduled(cron = "0 0/5  * * * *")
	 public void setToActiveOrder() throws Exception {

		    orderservice.setToActive();
		    lineNotifyService.sendLineNotifySticker("Active Order ที่  Upload ใหม่ ละนะ", 1, 423);
		}
	 
	 @Autowired
	 private OrdertService orderService;
	 
//	 @GetMapping("/search1")
//	 public List<Order> searchOrderByName(@RequestParam("product_name") String name){
//		 return orderService.findByproduct_name(name);
//	 }
//
//	 @GetMapping("/search2")
//	 public List<Order> searchOrderById(@RequestParam("orderID") Integer id){
//		 return orderService.findByOrderID(id);
//	 }
//	 
//	 @GetMapping("/search3")
//	 public List<Order> searchAll(@RequestParam(required = false, value="orderID") Integer id, @RequestParam(required = false, value="product_name") String name, @RequestParam(required = false, value="product_code") Integer code){
//		 return orderService.findByOrderIDOrproduct_nameOrproduct_code(id, name, code);
//	 }
	 
	 @GetMapping("/search")
	 public List<Order> searchAllCol(@RequestParam("keyword") String key){
		 String id = key;
		 String code = key;
		 String name = key;
		 String product = key;
		 String company = key;
		 Integer ppu;
		 Integer unit;
		 Float vat;
		 Integer total;
		 try {
			 ppu = Integer.parseInt(key);
			 unit = Integer.parseInt(key);
			 vat = Float.parseFloat(key);
			 total = Integer.parseInt(key);
		 }
		 catch(NumberFormatException e){
			 ppu = null;
			 unit = null;
			 vat = null;
			 total = null;
		 }
		 
		 return orderService.findByAllCol(id, code, name, product, company, ppu, unit,vat,total);
	 }
}
