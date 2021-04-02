package com.visio.rules.engine.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visio.rules.engine.persistance.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findAll();  
	
	public List<Product> findByName(String name); 
	
	public List<Product> findByInterestRateBetween(Double lowerInterest, Double higherInterest); 
	
	public List<Product> findByDisqualifiedFalse();
	
	public List<Product> findByDisqualifiedTrue();

}
