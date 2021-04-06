package com.visio.rules.engine.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visio.rules.engine.State;
import com.visio.rules.engine.persistance.Person;
import com.visio.rules.engine.persistance.PersonAction;
import com.visio.rules.engine.persistance.PersonCondition;
import com.visio.rules.engine.persistance.Product;
import com.visio.rules.engine.persistance.ProductAction;
import com.visio.rules.engine.persistance.ProductCondition;
import com.visio.rules.engine.persistance.RuleAction;
import com.visio.rules.engine.persistance.RuleCondition;
import com.visio.rules.engine.persistance.Rules;
import com.visio.rules.engine.repository.PersonActionRepository;
import com.visio.rules.engine.repository.PersonConditionRepository;
import com.visio.rules.engine.repository.PersonRepository;
import com.visio.rules.engine.repository.ProductActionRepository;
import com.visio.rules.engine.repository.ProductConditionRepository;
import com.visio.rules.engine.repository.ProductRepository;
import com.visio.rules.engine.repository.RulesRepository;
import com.visio.rules.engine.service.RulesService;

@RestController
@RequestMapping("/rules")
public class RulesController {
	
	@Autowired
	private RulesService service; 
	
	@Autowired
	private RulesRepository repo;
	
	@Autowired
	private PersonConditionRepository personCondRepo; 

	@Autowired
	private ProductConditionRepository prodCondRepo; 

	@Autowired
	private PersonActionRepository personActionRepo; 

	@Autowired
	private ProductActionRepository prodActionRepo; 

	
	@Autowired
	private PersonRepository persRepo; 
	
	@Autowired
	private ProductRepository prodRepo; 
	
	@RequestMapping(method=RequestMethod.POST, value="/runRules", produces="application/json; charset=UTF-8")
	public ResponseEntity<List<Object>> runRules(@RequestBody HashMap<String,HashMap<String,String>> runRulesJson){
		HashMap<String,String> condMap = runRulesJson.get("condition"); 
		HashMap<String,String> actionMap = runRulesJson.get("action");
		Person testPerson = null;
		Product testProduct = null;
		Product testProduct1 = null;
		Boolean productIsCond = false;
		Boolean productIsAction = false;
		
		//InputCondition
		if("Person".equals(condMap.get("type"))) {
			testPerson = persRepo.findById(Integer.valueOf(condMap.get("id"))).get();			 
		}else {
			testProduct = prodRepo.findById(Integer.valueOf(condMap.get("id"))).get();
			productIsCond = true;
		}
		
		//OutputReuslt
		if("Person".equals(actionMap.get("type"))) {
			testPerson = persRepo.findById(Integer.valueOf(actionMap.get("id"))).get();
		}else {
			testProduct1 = prodRepo.findById(Integer.valueOf(actionMap.get("id"))).get();
			productIsAction = true;
		}
		
		List<Rules> fullRuleSet = service.loadRules(); 
		if(productIsCond && productIsAction) {
			testProduct1 = service.applyProductActionRulesBasedOnProduct(testProduct, testProduct1, fullRuleSet); 
		}else if(productIsCond && !productIsAction) {
			testPerson = service.applyPersonActionRulesBasedOnProduct(testProduct, testPerson, fullRuleSet); 
		}else if(!productIsCond && productIsAction) {
			testProduct1 = service.applyProductActionRulesBasedOnPerson(testPerson, testProduct1, fullRuleSet); 
		}
		
		List<Object> responseObj = new ArrayList<Object>();
		responseObj.add(testPerson);
		responseObj.add(testProduct1);
		return ResponseEntity.ok(responseObj); 
	}

	@RequestMapping(method=RequestMethod.POST, value="/addRule", produces="application/json; charset=UTF-8")
	public ResponseEntity<Rules> addRule(@RequestBody HashMap<String,HashMap<String,String>> ruleJson){
		Rules newRule = new Rules(); 
		newRule.setCondition(buildConditionFromMap(ruleJson.get("condition")));
		newRule.setAction(buildActionFromMap(ruleJson.get("action")));
		
		
		newRule.setDateCreated(new Date(System.currentTimeMillis()));
		newRule.setDateUpdated(new Date(System.currentTimeMillis()));
		repo.save(newRule); 
		
		return ResponseEntity.ok(newRule);
		
	}
	
	public RuleCondition buildConditionFromMap(HashMap<String,String> conditionMap) { 
		RuleCondition ruleCond = new RuleCondition(); 
		
		if("Person".equals(conditionMap.get("type"))) {
			PersonCondition cond = new PersonCondition(); 
			cond.setIsCreditScoreRule(Boolean.valueOf(conditionMap.get("creditScoreRule"))); 
			cond.setCreditScore(Integer.valueOf(conditionMap.get("creditScore")));
			cond.setCreditScoreOperation(conditionMap.get("creditScoreOperation"));
			cond.setIsStateRule(Boolean.valueOf(conditionMap.get("stateRule")));
			cond.setDescription(conditionMap.get("condDescription"));
			String stateString = conditionMap.get("stateCheck"); 
			if((State.valueOfAbbreviation(stateString) != State.UNKNOWN) || 
					(State.valueOfName(stateString)!= State.UNKNOWN)) {
				if(State.valueOfAbbreviation(stateString) != State.UNKNOWN) {
					cond.setStateCheck(State.valueOfAbbreviation(stateString)); 
				}else if(State.valueOfName(stateString)!= State.UNKNOWN) {
					cond.setStateCheck(State.valueOfName(stateString)); 
				}else {
					cond.setStateCheck(State.UNKNOWN); 
				}
			}
			ruleCond = personCondRepo.save(cond); 
		}else {
			ProductCondition cond = new ProductCondition(); 
			cond.setIsNameRule(Boolean.valueOf(conditionMap.get("nameRule")));
			cond.setName(conditionMap.get("name"));
			cond.setNameOperation(conditionMap.get("nameOperation"));
			cond.setIsNameRule(Boolean.valueOf(conditionMap.get("interestRateRule")));
			cond.setInterestRate(Double.valueOf(conditionMap.get("interestRate")));
			cond.setInterestRateOperation(conditionMap.get("interestRateOperation"));
			cond.setIsDisqualifingRule(Boolean.valueOf(conditionMap.get("disqualifyingRule")));
			cond.setDisqualifyingCheck(Boolean.valueOf(conditionMap.get("disqualifying")));
			cond.setDescription(conditionMap.get("condDescription"));
			ruleCond = prodCondRepo.save(cond);
		}
		return ruleCond; 
	}
	
	private RuleAction buildActionFromMap(HashMap<String,String> actionMap) { 
		RuleAction ruleAction = new RuleAction(); 
		
		if("Person".equals(actionMap.get("type"))) {
			PersonAction action = new PersonAction(); 
			action.setIsCreditScoreAction(Boolean.valueOf(actionMap.get("creditScoreRule"))); 
			action.setCreditScore(Integer.valueOf(actionMap.get("creditScore")));
			action.setCreditScoreOperation(actionMap.get("creditScoreOperation"));
			action.setDescription(actionMap.get("actionDescription"));
			ruleAction = personActionRepo.save(action); 
		}else {
			ProductAction action = new ProductAction();
			action.setIsInterestRateAction(Boolean.valueOf(actionMap.get("interestRateRule")));
			action.setInterestRate(Double.valueOf(actionMap.get("interestRate")));
			action.setInterestRateOperation(actionMap.get("interestRateOperation"));
			action.setIsDisqualifingAction(Boolean.valueOf(actionMap.get("disqualifyingRule")));
			action.setDisqualifyChangeTo(Boolean.valueOf(actionMap.get("disqualifying")));
			action.setDescription(actionMap.get("actionDescription"));
			ruleAction = prodActionRepo.save(action); 
		}
		return ruleAction; 
	}

}
