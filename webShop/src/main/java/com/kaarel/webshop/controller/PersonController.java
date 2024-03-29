package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Person;
import com.kaarel.webshop.model.PersonDto;
import com.kaarel.webshop.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2//Iga klass tuleb eraldi annoteerida kui vaja logida
@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;


    //Get all persons localhost:8080/person
    @GetMapping("person")
    public ResponseEntity<List<Person>> getPersons() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info(email);
        return ResponseEntity.ok().body(personRepository.findAll());
    }

    @GetMapping("person-account")
    public Person getPersonAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return personRepository.findPersonByEmail(email);
    }

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("person-public")
    public ResponseEntity<List<PersonDto>> getPersonsPublic() {
        List<Person> persons = personRepository.findAll();

        List<PersonDto> personDtos = persons.stream()
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(personDtos);
    }

    //Get person by id
    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok().body(personRepository.findById(id).get());
    }


    // Delete person
    @DeleteMapping("person/{id}")
    public ResponseEntity<List<Person>> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().body(personRepository.findAll());
    }

    //POST localhost:8080/person
    @PutMapping("person")
    public List<Person> editPerson(@RequestBody Person person) {
        if (personRepository.existsById(person.getId())) {
            personRepository.save(person);
        }
        return personRepository.findAll();
    }
    //POST localhost:8080/person
//    @PostMapping("person")
//    public ResponseEntity<List<Person>> addPerson(@RequestBody Person person) throws Exception {
//        if (person.getId()==null ||!personRepository.existsById(person.getId())) {
//            personRepository.save(person);
//        }else{
//            throw new Exception("Id juba olemas");
//        }
//        return  ResponseEntity.status(HttpStatus.CREATED).body(personRepository.findAll());
//    }

//    @GetMapping("person-public2")
//    public List<PersonDto> getPersonsPublic2() {
//        List<Person> persons = personRepository.findAll();
//        List<PersonDto> personDtos = new ArrayList<>();
//        for (Person p : persons) {
//            PersonDto personDto = new PersonDto();
//            personDto.setFirstName(p.getFirstName());
//            personDto.setLastName(p.getFirstName());
//            personDto.setEmail(p.getEmail());
//            personDtos.add(personDto);
//
//        }
//        return personDtos;
//    }
}
