package com.kaarel.decathlon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
// -Võimalda salvestada sportlane – nimi, riik, vanus.
// -Järgmiseks tee 10 API päringut Controlleris, mis võtavad vastu iga kergejõustiku ala tulemuse
// -Kõik päringud arvutavad tulemuse punktideks (guugelda kuidas arvutada, aga ülemäära aega ei pea
//  kulutama – kui ei leia, siis tee tulemuse punktideks saamine tunde järgi).
// -Kõik tulemused peab ära salvestama andmebaasi.
// -Kõik tulemused peavad minema selle sportlase „külge“. Saada näiteks päringuga lisaks tulemusele
//  kaasa ka ID.
//
// -Võimalda saada sportlase kogusumma selleks hetkeks (kui on sisestatud 6 ala, siis 6 ala kogusumma)

@Entity //teeb tabeliks ja salvestab andmed
@Getter
@Setter
public class Athlete {
    @Id
    private String name;
    private String country;
    private Long age;
}
