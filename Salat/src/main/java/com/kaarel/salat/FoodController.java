package com.kaarel.salat;
//Salat
//*Tee uus veebiprojekt.
// Koosta klass Toiduaine tarbeks (nimetus, valkude, rasvade ja süsivesikute protsent).
// Protsent kokku ei saa ületada 100 - muidu antakse veateade - tee see API otspunktist andmeid võttes.
// Loo API otspunktist brauseri või Postmani abil mõni toiduaine ja pane ta andmebaasi (nt. kartul, hapukoor, vorst).
//      Andmed saab nt (https://tka.nutridata.ee/et/). Sealt kartul (https://tka.nutridata.ee/et/toidud/280).
// Koosta klass Toidukomponendi tarbeks (kogus, viide toiduainele - võõrvõtmena).
// Loo mõned toidukomponendid (nt. 100 g kartuleid, 30 g hapukoort, 50 g vorsti).
// Lisa toidukomponendile API otspunkt selle sees leiduva rasvakoguse arvutamiseks.

//*Koosta klass Toidu jaoks (koosneb nimetusest ja toidukomponentidest - võõrvõtmete list).
// Lisa toidule käsklused küsimaks sisalduvate valkude, rasvade ja süsivesikute kogust.
// Loo brauseri või Postmani abil retsepti järgi toit (nt. kartulisalat).
//*Võimalda otsida Toiduainet, Toidukomponenti kui ka Toitu nimetuse järgi (3 erinevat API otspunkti).
// Tee uus API otspunkt, mis küsib sisendiks rasva protsendi algväärtust ja lõppväärtust ning tagastab kõik Toidud kellel on rasvaprotsent sellest vahemikus.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    SalatRepository salatRepository;

    @GetMapping("salat")
    public Macros getFood(@RequestBody String name,
                          @RequestBody double fat,
                          @RequestBody double carb,
                          @RequestBody double proteine ){


        return null;
    }

    @GetMapping("salat")
    public List<Macros> getAllFoods(){
        return salatRepository.findAll();
    }

    @PostMapping("salat")
    public List<Macros> addFood(@RequestBody Macros macros){
        if (macros.getNameOfIngridient()==null || !salatRepository.existsById(macros.getNameOfIngridient())) {
            salatRepository.save(macros);
        }
        return salatRepository.findAll();
    }

    @PutMapping("salat")
    public List<Macros> editFood(@RequestBody Macros macros){
        if (salatRepository.existsById(macros.getNameOfIngridient())){
            salatRepository.save(macros);
        }
        return salatRepository.findAll();
    }

}
