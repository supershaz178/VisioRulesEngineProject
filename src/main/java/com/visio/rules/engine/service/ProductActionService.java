package com.visio.rules.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visio.rules.engine.persistance.Product;
import com.visio.rules.engine.persistance.ProductAction;
import com.visio.rules.engine.persistance.RuleAction;
import com.visio.rules.engine.persistance.Rules;
import com.visio.rules.engine.repository.ProductRepository;

@Service
public class ProductActionService {
		
	@Autowired
	private ProductRepository productRepo; 
	
	public Product applyActionsToProduct(Rules rule, Product changeProduct) {
		ProductAction action; 
		
		if(rule.getAction() instanceof ProductAction) {
			action = (ProductAction)rule.getAction(); 
			
			if(action.getIsInterestRateAction()) {
				changeProduct = changeInterestRate(action, changeProduct); 
			}
			if(action.getIsDisqualifingAction()) {
				changeProduct = changeDisqualify(action, changeProduct); 
			}
		}
		productRepo.save(changeProduct);
		
		return changeProduct; 
	}
	
	private Product changeInterestRate(ProductAction action,  Product product) {
		
		if(action.getInterestRateOperation().equals(RuleAction.INCREASE_RT)) {
			product.setInterestRate(product.getInterestRate() + action.getInterestRate()); 
		}else {
			product.setInterestRate(product.getInterestRate() - action.getInterestRate());
		}
		
		return product; 
	}

	private Product changeDisqualify(ProductAction action, Product product) {
		product.setDisqualified(action.getDisqualifyChangeTo()); 
		
		return product; 
	}
}
