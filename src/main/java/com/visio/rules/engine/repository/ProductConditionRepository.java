package com.visio.rules.engine.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visio.rules.engine.persistance.ProductCondition;

@Repository
@Transactional
public interface ProductConditionRepository extends JpaRepository<ProductCondition, Integer> {
	
	public List<ProductConditionRepository> findByIsNameRuleTrue(); 
	
	public List<ProductConditionRepository> findByIsInterestRateRuleTrue(); 
	
	public List<ProductConditionRepository> findByIsDisqualifyingRuleTrue(); 

}
