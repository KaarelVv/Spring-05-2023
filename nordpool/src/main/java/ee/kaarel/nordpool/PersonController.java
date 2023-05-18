package ee.kaarel.nordpool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    //tegelikkuses võtame andmebaasist
    Person isik1 = new Person(1L,"Kaarel","Viilvere","372");

    @GetMapping("get-person")
    public Person getPerson(){
        return isik1;
    }

    @GetMapping("change-firstname/{firstName}") //localhost:8080/change-firstname
    public Person changeFirstName(@PathVariable String firstName){
        isik1.setFirsName(firstName);
        return isik1;
    }
}
