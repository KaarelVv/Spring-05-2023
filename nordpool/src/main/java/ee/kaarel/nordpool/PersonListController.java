package ee.kaarel.nordpool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PersonListController {

    Person isik1 = new Person(1L,"Kaarel","Viilvere","372");
    Person isik2 = new Person(2L,"David","Hoffman","388");
    Person isik3 = new Person(3L,"Joe","Wiseman","40");
    List<Person> persons = new ArrayList<>(Arrays.asList(isik1,isik2,isik3));// tekitan listi ja loon selle listii kohe


    @GetMapping("persons")
    public List<Person> getPersons(){
        return persons;
    }

    //localhost:8080/add-person/4/Mart/Kivi/31535 -- nii on jama
    //
    @GetMapping("add-person")
    public List<Person> addPersons(@RequestParam Long id,@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber){
        Person person = new Person(id,firstName,lastName,phoneNumber);
        persons.add(person);
        return persons;
    }
    //localhost:8080/delete-person/1
    @GetMapping("delete-person/{index}")//@DeleteMapping
    public List<Person> deletePersons(@PathVariable int index){
        persons.remove(index);
        return persons;
    }
//@GetMapping - selleks,et saada brauseris päringuid läbi viia
}

// kasutajad,telliumsed,poed
