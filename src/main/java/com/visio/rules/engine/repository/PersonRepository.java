package com.visio.rules.engine.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visio.rules.engine.persistance.Person;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public List<Person> findAll();
	
	public List<Person> findByState(String state); 
	
	public List<Person> findByCreditScoreBetween(Integer lower, Integer higher); 
	

}
