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

import org.springframework.web.bind.annotation.*;

@RestController
    public class DecathlonController {
    @GetMapping("/athlete/{name}")
    public Long getAthlete(@PathVariable String name, @RequestParam Long age, @RequestParam Long result) {
        Athlete athlete = new Athlete();
        athlete.setName(name);
        athlete.setAge(age);
        athlete.setResult(result);
        Calculation calculation = new Calculation(athlete);
        return calculation.getValue();
    }

    @GetMapping("/calculation/{field}")
    public Calculation resultCalculation(@PathVariable String field, @RequestParam String athleteName, @RequestParam Long athleteAge, @RequestParam Long athleteResult) {
        return null;
    }
}

