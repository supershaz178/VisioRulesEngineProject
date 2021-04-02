package com.visio.rules.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visio.rules.engine.persistance.Person;
import com.visio.rules.engine.persistance.PersonAction;
import com.visio.rules.engine.persistance.RuleAction;
import com.visio.rules.engine.persistance.Rules;
import com.visio.rules.engine.repository.PersonRepository;

@Service
public class PersonActionService {
		
	@Autowired
	private PersonRepository personRepo; 
		
	public Person applyActionsToPerson(Rules rule, Person changePerson) {
		PersonAction action; 
		
		if(rule.getAction() instanceof PersonAction) {
			action = (PersonAction)rule.getAction(); 
			
			if(action.getIsCreditScoreAction()) {
				changePerson = changeCreditScore(action, changePerson); 
			}
		}
		personRepo.save(changePerson);
		
		return changePerson; 
	}
	
	private Person changeCreditScore(PersonAction action,  Person person) {
		
		if(action.getCreditScoreOperation().equals(RuleAction.INCREASE_RT)) {
			person.setCreditScore(person.getCreditScore() + action.getCreditScore()); 
		}else {
			person.setCreditScore(person.getCreditScore() - action.getCreditScore());
		}
		
		return person; 
	}


}
