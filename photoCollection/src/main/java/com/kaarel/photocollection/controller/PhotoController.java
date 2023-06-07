package com.kaarel.photocollection.controller;
import com.kaarel.photocollection.model.PhotoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//Sul on fotode kogumik, mida hoiab hoopis teine rakendus enda sees. Pead tagastama front-endile kõik pildid, mis fotode kogumikus selles teises rakenduses on.
// Pole mõtet neid vahepeal meie andmebaasi panna, sest sina ei muuda, lisa ega kustuta neid OMA rakenduses – sinu ülesanne on võtta neid nii nagu on.

//Aadress fotode saamiseks: https://jsonplaceholder.typicode.com/photos

//Tee olemasolevasse projekti (või ka uus projekt) kõikide fotode võtmine API endpointi kaudu.

//Selleks tee uus Controller ja sinna sisse GetMapping.

//Testi enne GET päringut Postmanis. Meil EI OLE vaja anda body ega muuta headereid -> seega HttpEntity võib olla tühi (null).

//Tagastusklass loo aga täpselt nii nagu peab, võid kasutada ka converteri abi:
//https://www.site24x7.com/tools/json-to-java.html

//Pane tähele, et tagastuses on seekord tegemist Listiga, mitte ühe objektiga:
//Kui pead määrama tagastuse restTemplate.exchange() sees, siis tee UusKlass[].class
//(me tegime OmnivaParcelMachine[].class)
//
//Tagasta Controlleri lõpus front-endile kõik albumid, mida sa sellest rakendusest saad, front-end juba tegeleb seejärel nende kasutajale kuvamisega.
@RestController
public class PhotoController {

    @GetMapping("photos")
    public List<Integer> takePhotos(){

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/photos";

        ResponseEntity<PhotoResponse[]> response = restTemplate.exchange(url, HttpMethod.GET,null, PhotoResponse[].class);

        return Arrays.stream(response.getBody()).map(e-> e.albumId).collect(Collectors.toList());
    }
}
