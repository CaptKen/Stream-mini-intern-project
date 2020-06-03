package com.example.order;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@EnableAutoConfiguration
public class OrderController {
	@Autowired
    OrdertService productservice;
	@Autowired
	private OrderRepository pR;
	
	private static String readTextFile() 
    {
        StringBuilder contentBuilder = new StringBuilder();
        String filePath = "order(s).txt";
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
	
	List<Order> orders = new ArrayList();
	@PostMapping("/create")
	public void createOrdersObject() {
		
		String plainText = readTextFile();
//        String testText = "0002|000002|AirMax|5600.00|30|13.00|168000.00|0003|000003|Air Jordan|5600.00|10|13.00|560000.00";
		String[] strs = plainText.split("\\|");
		
		
		
		int index = 0;
        for(int i=0; i<(strs.length)/7; i++) {
        	
    		int order_id = Integer.parseInt(strs[index]);
    		int product_code = Integer.parseInt(strs[index+1]);
    		String product_name = strs[index+2];
    		double price_per_unit = Double.parseDouble(strs[index+3]);
    		int unit = Integer.parseInt(strs[index+4]);
    		float vat = Float.parseFloat(strs[index+5]);
    		double total_price = Double.parseDouble(strs[index+6]);
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
        System.out.print(orders);
	}
   
	 @GetMapping("/order")
	 public List<Order> getAllOrder() {
		   return productservice.getAllOrder();
		}

}
