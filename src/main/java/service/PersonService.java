package service;

import dao.PersonRepository;
import model.entity.Person;
import model.entity.Spouse;

public class PersonService {
    PersonRepository<Spouse> personRepository=new PersonRepository<Spouse>();
    public void save(Person person){
        personRepository.save(person);
    }
}
