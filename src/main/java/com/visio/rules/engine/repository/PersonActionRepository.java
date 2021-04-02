package com.visio.rules.engine.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visio.rules.engine.persistance.PersonAction;

@Repository
@Transactional
public interface PersonActionRepository extends JpaRepository<PersonAction, Integer> {
	
	public List<PersonAction> findByIsCreditScoreActionTrue();

}
