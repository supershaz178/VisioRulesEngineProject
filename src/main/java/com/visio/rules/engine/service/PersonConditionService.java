package com.visio.rules.engine.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visio.rules.engine.persistance.Person;
import com.visio.rules.engine.persistance.PersonCondition;
import com.visio.rules.engine.persistance.RuleCondition;
import com.visio.rules.engine.repository.PersonConditionRepository;

@Service
public class PersonConditionService {

	@Autowired
	public PersonConditionRepository repo;

	public HashSet<PersonCondition> basedConditionsToPerson(Person person){
		List<PersonCondition> personConditions = repo.findAll(); 
		HashSet<PersonCondition> personBasedOn = new HashSet<PersonCondition>(); 
		
		for(PersonCondition cond : personConditions) {
			if(isStateConditionApply(cond, person)) {
				personBasedOn.add(cond); 
			}if(isCreditScoreConditionApply(cond, person)) {
				personBasedOn.add(cond);
			}
		}
		
		return personBasedOn; 
	}

	private Boolean isStateConditionApply(PersonCondition cond, Person person) {
		Boolean result = false;

		if ((cond.getIsStateRule()) && cond.getStateCheck().getAbbreviation()
				.equals(person.getState().getAbbreviation())) {
			return true;
		}

		return result;
	}

	private Boolean isCreditScoreConditionApply(PersonCondition cond,
			Person person) {
		Boolean result = false;

		if (cond.getIsCreditScoreRule()) {
			switch (cond.getCreditScoreOperation()) {
			case RuleCondition.LESS_THAN:
				if (person.getCreditScore() < cond.getCreditScore()) {
					result = true;
				}
				break;
			case RuleCondition.GREATER_THAN:
				if (person.getCreditScore() > cond.getCreditScore()) {
					result = true;
				}
				break;
			case RuleCondition.LESS_THAN_EQUAL:
				if (person.getCreditScore() <= cond.getCreditScore()) {
					result = true;
				}
				break;
			case RuleCondition.GREATER_THAN_EQUAL:
				if (person.getCreditScore() >= cond.getCreditScore()) {
					result = true;
				}
				break;
			case RuleCondition.EQUAL:
				if (person.getCreditScore() == cond.getCreditScore()) {
					result = true;
				}
				break;
			case RuleCondition.NOT_EQUAL:
				if (person.getCreditScore() != cond.getCreditScore()) {
					result = true;
				}
				break;

			}

		}

		return result;
	}
}
