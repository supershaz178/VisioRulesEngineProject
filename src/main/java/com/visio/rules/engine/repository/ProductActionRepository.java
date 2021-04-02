package com.visio.rules.engine.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visio.rules.engine.persistance.ProductAction;

@Repository
@Transactional
public interface ProductActionRepository extends JpaRepository<ProductAction, Integer> {
	
	public List<ProductAction> findByIsInterestRateActionTrue(); 
	
	public List<ProductAction> findByIsDisqualifingActionTrue(); 
}
