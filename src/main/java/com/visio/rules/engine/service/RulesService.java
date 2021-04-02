package com.visio.rules.engine.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visio.rules.engine.persistance.Person;
import com.visio.rules.engine.persistance.PersonCondition;
import com.visio.rules.engine.persistance.Product;
import com.visio.rules.engine.persistance.ProductCondition;
import com.visio.rules.engine.persistance.Rules;
import com.visio.rules.engine.repository.RulesRepository;

@Service
public class RulesService {
	
	@Autowired
	private RulesRepository rulesRepo;
	
	@Autowired
	private PersonConditionService persCondService; 
	
	@Autowired 
	private ProductConditionService prodCondService; 

	@Autowired
	private PersonActionService persActService; 
	
	@Autowired 
	private ProductActionService prodActService; 
	
	public List<Rules> loadRules(){
		return rulesRepo.findAll(); 
	}
	 
	
	public Product applyProductActionRulesBasedOnPerson(Person person, Product product, List<Rules> fullRulesSet){
		HashSet<Rules> rulesToApply = new HashSet<Rules>(); 
		
		//Find the conditions that are triggered by the person input
		HashSet<PersonCondition> conditions = persCondService.basedConditionsToPerson(person); 
		
		//Filter rules based on conditions found
		for(PersonCondition cond : conditions) {
			for(Rules rule : fullRulesSet) {
				if(rule.getCondition() instanceof PersonCondition) {
					PersonCondition ruleCond = (PersonCondition)rule.getCondition(); 
					if(ruleCond.getId() == cond.getId()) {
						rulesToApply.add(rule);
					}
				//If the rule applies to ALL products, leave condition null
				}if(rule.getCondition() == null) {
					rulesToApply.add(rule);
				}
			}
		}
		//Apply Product actions from filtered rules
		for(Rules rule : rulesToApply) {
			product = prodActService.applyActionsToProduct(rule, product); 
		}
		//Output products that were changed
		
		return product; 
	}
	
	public Product applyProductActionRulesBasedOnProduct(Product productCond, Product productAct, List<Rules> fullRuleSet){
		HashSet<Rules> rulesToApply = new HashSet<Rules>(); 
		
		//Find the conditions that are triggered by the person input
		HashSet<ProductCondition> conditions = prodCondService.basedConditionsToProduct(productCond); 

		//Filter rules based on conditions found 
		for(ProductCondition cond : conditions) {
			for(Rules rule : fullRuleSet) {
				if(rule.getCondition() instanceof ProductCondition) {
					ProductCondition ruleCond = (ProductCondition)rule.getCondition(); 
					if(ruleCond.getId() == cond.getId()) {
						rulesToApply.add(rule);
					}
				//If the rule's action applies to ALL products, leave condition null
				}if(rule.getCondition() == null) {
					rulesToApply.add(rule);
				}
			}
		}

		//Apply Product actions from filtered rules
		for(Rules rule : rulesToApply) {
			productAct = prodActService.applyActionsToProduct(rule, productAct); 
		}
		
		//Output products that were changed
		return productAct; 
	}
	
	public Person applyPersonActionRulesBasedOnProduct(Product product, Person person, List<Rules> fullRuleSet){
		HashSet<Rules> rulesToApply = new HashSet<Rules>(); 
		
		//Find the conditions that are triggered by the person input
		HashSet<ProductCondition> conditions = prodCondService.basedConditionsToProduct(product); 
		
		//Filter rules based on conditions found 
		for(ProductCondition cond : conditions) {
			for(Rules rule : fullRuleSet) {
				if(rule.getCondition() instanceof ProductCondition) {
					ProductCondition ruleCond = (ProductCondition)rule.getCondition(); 
					if(ruleCond.getId() == cond.getId()) {
						rulesToApply.add(rule);
					}
				//If the rule's action applies to ALL products, leave condition null
				}if(rule.getCondition() == null) {
					rulesToApply.add(rule);
				}
			}
		}

		//Apply Product actions from filtered rules
		for(Rules rule : rulesToApply) {
			person = persActService.applyActionsToPerson(rule, person); 
		}
		//Output Person objects that were changed
		return person; 
	}
}
