package com.visio.rules.engine.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visio.rules.engine.persistance.Rules;

@Repository
@Transactional
public interface RulesRepository extends JpaRepository<Rules, Integer> {

	public List<Rules> findByCondition(Integer conditionId);

	public List<Rules> findByAction(Integer actionId);
	
	public Rules findByConditionAndAction(Integer conditionId, Integer actionId);

	public List<Rules> findByDateCreated(Date dateCreated);

	public List<Rules> findByDateUpdated(Date dateUpdated);

}
