package com.visio.rules.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visio.rules.engine.persistance.PersonCondition;


public interface PersonConditionRepository extends JpaRepository<PersonCondition, Integer> {
	
	public List<PersonCondition> findByIsCreditScoreRuleTrue(); 
	
	public List<PersonCondition> findByIsStateRuleTrue(); 

}
