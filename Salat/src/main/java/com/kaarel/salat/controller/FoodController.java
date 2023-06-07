package com.kaarel.salat.controller;
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

import com.kaarel.salat.entity.Ingredients;
import com.kaarel.salat.repository.SalatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class FoodController {
    @Autowired
    SalatRepository salatRepository;
    @GetMapping("food")
    public List<Ingredients> getFood(){
        return salatRepository.findAll();
    }
    @GetMapping("addfood")
    public List<Ingredients> addFood1(@RequestParam String name,
                                     @RequestParam Double fat, @RequestParam Double carb,
                                     @RequestParam Double protein) {
        var cake = new Ingredients();
        cake.setIngredientName(name);
        cake.setFat(fat);
        cake.setCarb(carb);
        cake.setProtein(protein);

        Ingredients savedFood = salatRepository.save(cake);

        try {
            savedFood.validateMacro();
            System.out.println("Macro validation successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Macro validation failed: " + e.getMessage());
        }
        return salatRepository.findAll();
    }
    @PostMapping("/food")
    public ResponseEntity<String> addFood(@RequestBody Ingredients food) {

        try {
            food.validateMacro();
            Ingredients savedFood = salatRepository.save(food);
            System.out.println("Macro validation successful for food: " + savedFood.getIngredientName());
            return ResponseEntity.ok("Food added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Macro validation failed for this ingredient:  " + food.getIngredientName() + ": " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("food/fat")
    public Double getTotalFatContent() {
        List<Ingredients> ingredientsList = salatRepository.findAll();
        Double totalFatContent = 0.0;

        for (Ingredients ingredient : ingredientsList) {
            totalFatContent += ingredient.getFat();
        }
        return totalFatContent;
    }

}
