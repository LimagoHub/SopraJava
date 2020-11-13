package de.sopra.services;

import de.sopra.persistence.PersonRepository;
import de.sopra.persistence.models.Person;

public class PersonServiceImpl {
	
	private final PersonRepository repository;

	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}
	
	public void tuewas() {
		try {
			Person john = new Person("Max","Mustermann");
			repository.save(john);
			System.out.println(john);
			
			Person x = repository.findByPrimaryKey(900);
			System.out.println(x);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
