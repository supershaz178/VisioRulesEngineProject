package com.visio.rules.engine.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visio.rules.engine.State;
import com.visio.rules.engine.persistance.Person;
import com.visio.rules.engine.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepo; 
	
	@RequestMapping(method=RequestMethod.POST, value="/addPerson", produces="application/json; charset=UTF-8")
	public ResponseEntity<Person> addPerson(@RequestBody Map<String,String> newPersonJson){
		Person newPerson = buildPersonFromMap(newPersonJson); 
		
		newPerson.setDateCreated(new Date(System.currentTimeMillis()));
		newPerson.setDateUpdated(new Date(System.currentTimeMillis()));
		personRepo.save(newPerson); 
		
		return ResponseEntity.ok(newPerson); 
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/displayPerson", produces="application/json; charset=UTF-8")
	public ResponseEntity<List<Person>> getAllPerson(){
		Iterable<Person> studentsIter = personRepo.findAll();
		List<Person> allStudents = new ArrayList<Person>();
		ResponseEntity<List<Person>> entity; 
		
		for(Person s : studentsIter){
			allStudents.add(s); 
		}
		
		if(allStudents.isEmpty() || allStudents == null){
			entity = new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT); 
		}else{
			entity = new ResponseEntity<List<Person>>(allStudents, HttpStatus.OK); 
		}
				
		return entity;  
	}


	
	private Person buildPersonFromMap(Map<String,String> personJson) {
		Person newPerson = new Person(); 
		
		newPerson.setCreditScore(Integer.valueOf(personJson.get("creditScore")));
		
		String stateString = personJson.get("state"); 
		if((State.valueOfAbbreviation(stateString) != State.UNKNOWN) || 
				(State.valueOfName(stateString)!= State.UNKNOWN)) {
			if(State.valueOfAbbreviation(stateString) != State.UNKNOWN) {
				newPerson.setState(State.valueOfAbbreviation(stateString)); 
			}else if(State.valueOfName(stateString)!= State.UNKNOWN) {
				newPerson.setState(State.valueOfName(stateString)); 
			}else {
				newPerson.setState(State.UNKNOWN); 
			}
		}
		
		return newPerson; 
	}
}
