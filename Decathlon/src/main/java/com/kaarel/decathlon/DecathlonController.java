package com.kaarel.decathlon;
// -Võimalda salvestada sportlane – nimi, riik, vanus.
// -Järgmiseks tee 10 API päringut Controlleris, mis võtavad vastu iga kergejõustiku ala tulemuse
// -Kõik päringud arvutavad tulemuse punktideks (guugelda kuidas arvutada, aga ülemäära aega ei pea
//  kulutama – kui ei leia, siis tee tulemuse punktideks saamine tunde järgi).
// -Kõik tulemused peab ära salvestama andmebaasi.
// -Kõik tulemused peavad minema selle sportlase „külge“. Saada näiteks päringuga lisaks tulemusele
//  kaasa ka ID.
//
// -Võimalda saada sportlase kogusumma selleks hetkeks (kui on sisestatud 6 ala, siis 6 ala kogusumma)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    public class DecathlonController {

    @Autowired
            AthleteRepository athleteRepository;
            ResultRepository resultRepository;


    @GetMapping("/athlete")
    public Athlete addAthlete(@RequestParam String name,
                              @RequestParam String country,
                              @RequestParam Long age) {
        Athlete athlete = new Athlete();
        athlete.setName(name);
        athlete.setCountry(country);
        athlete.setAge(age);
        athleteRepository.save(athlete);
        return athlete;
    }

//    http://localhost:8080/calculation?fieldName=HUNDRED_METERS&result=12.5 (original)
//    http://localhost:8080/calculation?fieldName=TWO_HUNDRED_METERS&result=12.5
//    http://localhost:8080/calculation?fieldName=LONG_JUMP&result=12.5
//    http://localhost:8080/calculation?fieldName=HIGH_JUMP&result=12.5
//    http://localhost:8080/calculation?fieldName=SHOT_PUT&result=12.5
//    http://localhost:8080/calculation?fieldName=DISCUS_THROW&result=12.5
//    http://localhost:8080/calculation?fieldName=POLE_VAULT&result=12.5
//    http://localhost:8080/calculation?fieldName=JAVELIN_THROW&result=12.5
//    http://localhost:8080/calculation?fieldName=1500_METERS&result=12.5
//    http://localhost:8080/calculation?fieldName=MARATHON&result=12.5
    @GetMapping("/calculation")
    public double calculateValue(@RequestParam String fieldName,
                                 @RequestParam double result) {
        Calculation calculation = new Calculation(fieldName, result);
        double calculatedValue = calculation.getValue();

        Result resultEntity = new Result();
        resultEntity.setFieldName(fieldName);
        resultEntity.setResultValue(calculatedValue);
        resultRepository.save(resultEntity);

        return calculatedValue;
    }



}


