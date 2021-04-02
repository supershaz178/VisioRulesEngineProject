package com.visio.rules.engine.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visio.rules.engine.persistance.Product;
import com.visio.rules.engine.persistance.ProductCondition;
import com.visio.rules.engine.persistance.RuleCondition;
import com.visio.rules.engine.repository.ProductConditionRepository;

@Service
public class ProductConditionService {

	@Autowired
	private ProductConditionRepository prodCondRepo;

	/*ApplyConditionsToProduct finds all the rules 
	 * that are based on product conditions that 
	 * can be applied to a single product */
	public HashSet<ProductCondition> basedConditionsToProduct(Product product) {
		List<ProductCondition> productConditions = prodCondRepo.findAll();
		HashSet<ProductCondition> productBasedOn = new HashSet<ProductCondition>(); 
		
		for (ProductCondition cond : productConditions) {
			if (isNameConditionApply(cond, product)) {
				productBasedOn.add(cond); 
			}if(isInterestConditionApply(cond, product)) {
				productBasedOn.add(cond);
			}if(isDisqualifyingConditionApply(cond, product)) {
				productBasedOn.add(cond);
			}
		}

		return productBasedOn;
	}

	private Boolean isNameConditionApply(ProductCondition cond, Product product) {
		Boolean result = false; 
		
		if(cond.getIsNameRule()) {
			if(product.getName().equals(cond.getName())) {
				result = true; 
			}
		}
		
		return result;
	}
	
	private Boolean isInterestConditionApply(ProductCondition cond, Product product) {
		Boolean result = false; 
		
		if(cond.getIsInterestRateRule()) {
			switch(cond.getInterestRateOperation()) {
				case RuleCondition.LESS_THAN:
					if(product.getInterestRate() < cond.getInterestRate()) {
						result = true; 
					}
				break; 
				case RuleCondition.GREATER_THAN:
					if(product.getInterestRate() > cond.getInterestRate()) {
						result = true; 
					}
				break; 
				case RuleCondition.LESS_THAN_EQUAL:
					if(product.getInterestRate() <= cond.getInterestRate()) {
						result = true; 
					}
				break; 
				case RuleCondition.GREATER_THAN_EQUAL:
					if(product.getInterestRate() >= cond.getInterestRate()) {
						result = true; 
					}
				break; 
				case RuleCondition.EQUAL:
					if(product.getInterestRate() == cond.getInterestRate()) {
						result = true; 
					}
				break; 
				case RuleCondition.NOT_EQUAL:
					if(product.getInterestRate() != cond.getInterestRate()) {
						result = true; 
					}
				break; 

			}
		}
		
		return result; 
	}
	
	private Boolean isDisqualifyingConditionApply(ProductCondition cond, Product product) {
		Boolean result = false; 
		
		if((cond.getIsDisqualifingRule()) && (cond.getDisqualifyingCheck() == product.getDisqualified())) {
			return true; 
		}
		return result; 
	}
}
