package com.visio.rules.engine.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visio.rules.engine.persistance.Product;
import com.visio.rules.engine.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository prodRepo; 

	@RequestMapping(method=RequestMethod.POST, value="/addProduct", produces="application/json; charset=UTF-8")
	public ResponseEntity<Product> addPerson(@RequestBody Map<String,String> newProudctJson){
		Product newProduct = buildProductFromMap(newProudctJson); 
		
		newProduct.setDateCreated(new Date(System.currentTimeMillis()));
		newProduct.setDateUpdated(new Date(System.currentTimeMillis()));
		prodRepo.save(newProduct); 
		
		return ResponseEntity.ok(newProduct); 
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/displayProduct", produces="application/json; charset=UTF-8")
	public ResponseEntity<List<Product>> getAllProduct(){
		Iterable<Product> studentsIter = prodRepo.findAll();
		List<Product> allStudents = new ArrayList<Product>();
		ResponseEntity<List<Product>> entity; 
		
		for(Product s : studentsIter){
			allStudents.add(s); 
		}
		
		if(allStudents.isEmpty() || allStudents == null){
			entity = new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT); 
		}else{
			entity = new ResponseEntity<List<Product>>(allStudents, HttpStatus.OK); 
		}
				
		return entity;  
	}
	
	private Product buildProductFromMap(Map<String, String> productMap) {
		Product newProduct = new Product(); 
		
		newProduct.setName(productMap.get("name"));
		newProduct.setInterestRate(Double.valueOf(productMap.get("interestRate")));
		newProduct.setDisqualified(Boolean.valueOf(productMap.get("disqualified")));
		
		return newProduct; 
	}

}
