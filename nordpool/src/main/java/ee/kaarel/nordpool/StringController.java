package ee.kaarel.nordpool;
//kõik kontroller tüüpi failid on nneed mis suhtevad eesrakendusega(frontend)

//MVC Model-View-Controller

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//Model - constructorid, getterid, setterid
//view - React, Angular, Vue
//Controller
@RestController // Annotatsioon - tegemist on cotrolleriga
public class StringController {

    @GetMapping(path = "hello")//Tehakse võtmise päring (localhost:8080/hello)
    public String getDate(){
        return "Hello world at " + new Date();

        //@PathVariable - kinlasti inmporida
        //@PathVariable String - üritab konverteerida selleks tüübiks
        //@PathVariable String name - nimetus peab olema täpslet sama kui {name}
    } @GetMapping( "hi/{name}")//Tehakse võtmise päring (localhost:8080/hi/{name})
    public String getNate(@PathVariable String name){
        return "Hello " + name;
    }

    @GetMapping( "add/{first}/{second}")//Tehakse võtmise päring (localhost:8080/add/{first}/{second})
    public int add(@PathVariable int first, @PathVariable int second){
        return first + second;
    }@GetMapping( "multiply/{first}/{second}")//Tehakse võtmise päring (localhost:8080/multiply/{first}/{second})
    public int multiply(@PathVariable int first, @PathVariable int second){
        return first * second;
    }
}


//404 - not found, ei ole olemas või taaskäivita
//400 - mingisugune viga koos vea kirjeldusega
