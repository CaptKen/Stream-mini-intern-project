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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@EnableAutoConfiguration
public class OrderController {
	@Autowired
    OrdertService productservice;
	@Autowired
	private OrderRepository pR;
	
//	private static String readTextFile() 
//    {
//        StringBuilder contentBuilder = new StringBuilder();
//        String filePath = "order.txt";
//        
//        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
//        {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//        catch (IOException e) 
//        {
//            e.printStackTrace();
//        }
//        return contentBuilder.toString();
//    }
	
	List<Order> orders = new ArrayList();
	@PostMapping("/create")
	public void createOrdersObject(@RequestParam("inText") String[] txt) {		
		String[] strs = txt;	
		int index = 0;
		
        for(int i=0; i<(strs.length)/7; i++) {
    		int order_id = Integer.parseInt(strs[index]);
    		int product_code = Integer.parseInt(strs[index+1]);
    		String product_name = strs[index+2];
    		int price_per_unit = Integer.parseInt(strs[index+3]);
    		int unit = Integer.parseInt(strs[index+4]);
    		float vat = Float.parseFloat(strs[index+5]);
    		int total_price = Integer.parseInt(strs[index+6]);
        	Order o = new Order();
        	o.setOrderID(order_id);
        	o.setProduct_code(product_code);
        	o.setProduct_name(product_name);
        	o.setPrice_per_unit(price_per_unit);
        	o.setUnit(unit);
        	o.setVat(vat);
        	o.setTotal_price(total_price);
        	pR.save(o);
        	index += 7;
        }
    }
	
//	@PostMapping("/create")
//	public void createOrdersObject(@RequestParam("textFile")  File txt) {
//		
//		
//		String plainText = readTextFile();
////        String testText = "0002|000002|AirMax|5600.00|30|13.00|168000.00|0003|000003|Air Jordan|5600.00|10|13.00|560000.00";
//		
//		String[] strs = null;
//		try {
//		      File myObj = txt;
//		      Scanner myReader = new Scanner(myObj);
//		      while (myReader.hasNextLine()) {
//		        String data = myReader.nextLine();
//		        strs = plainText.split("\\|");
////		        System.out.println(data);
//		      }
//		      myReader.close();
//		    } catch (FileNotFoundException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
//		    }
//		
//		
//		
//		int index = 0;
//        for(int i=0; i<(strs.length)/7; i++) {
//        	
//    		int order_id = Integer.parseInt(strs[index]);
//    		int product_code = Integer.parseInt(strs[index+1]);
//    		String product_name = strs[index+2];
//    		int price_per_unit = Integer.parseInt(strs[index+3]);
//    		int unit = Integer.parseInt(strs[index+4]);
//    		float vat = Float.parseFloat(strs[index+5]);
//    		int total_price = Integer.parseInt(strs[index+6]);
//        	Order o = new Order();
//        	o.setOrderID(order_id);
//        	o.setProduct_code(product_code);
//        	o.setProduct_name(product_name);
//        	o.setPrice_per_unit(price_per_unit);
//        	o.setUnit(unit);
//        	o.setVat(vat);
//        	o.setTotal_price(total_price);
//        	pR.save(o);
//        	index += 7;
//        }
//        System.out.print(orders);
//	}
   
	 @GetMapping("/order")
	 public List<Order> getAllOrder() {
		   return productservice.getAllOrder();
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
		 Integer id;
		 Integer code;
		 String name = key;
		 Integer ppu;
		 Integer unit;
		 Float vat;
		 Integer total;
		 try {
			 id = Integer.parseInt(key);
			 code = Integer.parseInt(key);
			 ppu = Integer.parseInt(key);
			 unit = Integer.parseInt(key);
			 vat = Float.parseFloat(key);
			 total = Integer.parseInt(key);
		 }
		 catch(NumberFormatException e){
			 id = null;
			 code = null;
			 ppu = null;
			 unit = null;
			 vat = null;
			 total = null;
		 }
		 
		 return orderService.findByAllCol(id, code, name, ppu, unit,vat,total);
	 }
}
