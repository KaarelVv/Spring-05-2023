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
    public Food getFood() {
        Food kartul = new Food("Kartul", 0.1,15.5,1.9);
        return  kartul.validateMacro() ;
    }

    @GetMapping("salat")
    public List<Food> getAllFoods(){
        return salatRepository.findAll();
    }

    @PostMapping("salat")
    public List<Food> addFood(@RequestBody Food food){
        if (food.getName()==null || !salatRepository.existsById(food.getName())) {
            salatRepository.save(food);
        }
        return salatRepository.findAll();
    }

    @PutMapping("salat")
    public List<Food> editFood(@RequestBody Food food){
        if (salatRepository.existsById(food.getName())){
            salatRepository.save(food);
        }
        return salatRepository.findAll();
    }

}
