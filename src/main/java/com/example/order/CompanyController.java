package com.example.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.CompanyService;

@RestController
//@EnableAutoConfiguration
public class CompanyController {
	@Autowired
    CompanyService companyService;
	@GetMapping(value = "/company")
	public List<Company> getCompany() {
		
	   return companyService.getAllCompany();
	}
}
